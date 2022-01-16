// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NeckConstants;

public class NeckSubsystem extends SubsystemBase {
  /** Creates a new NeckSubsystem. */
  WPI_TalonSRX neckRollerFront, neckRollerBack;
  DigitalInput beamBreak;
  public NeckSubsystem() {
    neckRollerFront = new WPI_TalonSRX(NeckConstants.NECK_MOTOR);
    neckRollerBack = new WPI_TalonSRX(NeckConstants.NECK_MOTOR);
    beamBreak = new DigitalInput(NeckConstants.NECK_BEAMBREAK);
  }
  public void spinNeck(double speed) {
    neckRollerFront.set(speed);
    neckRollerBack.set(-speed);
  }
  public void stopNeck() {
    neckRollerFront.set(0);
    neckRollerBack.set(0);
  }
  public boolean getBeamBreak() {
    return beamBreak.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
