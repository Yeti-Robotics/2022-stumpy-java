// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NeckConstants;

public class NeckSubsystem extends SubsystemBase {
  /** Creates a new NeckSubsystem. */
  WPI_TalonFX neckRoller;
  DigitalInput beamBreak;
  public NeckSubsystem() {
    neckRoller = new WPI_TalonFX(NeckConstants.NECK_MOTOR);
    beamBreak = new DigitalInput(NeckConstants.NECK_BEAMBREAK);
  }
  public void spinNeck(double speed) {
    neckRoller.set(speed);
  }
  public void stopNeck() {
    neckRoller.set(0);
  }
  public boolean getBeamBreak() {
    return beamBreak.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
