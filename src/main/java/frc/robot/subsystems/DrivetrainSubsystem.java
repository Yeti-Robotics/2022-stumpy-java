// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {
  private WPI_TalonSRX leftMotor3, rightMotor2, rightMotor3;
  private WPI_VictorSPX leftMotor1, leftMotor2, rightMotor1;

  public DrivetrainSubsystem() {
    leftMotor1 = new WPI_VictorSPX(DrivetrainConstants.LEFT_MOTOR_1);
    leftMotor2 = new WPI_VictorSPX(DrivetrainConstants.LEFT_MOTOR_2);
    leftMotor3 = new WPI_TalonSRX(DrivetrainConstants.LEFT_MOTOR_3);
    rightMotor1 = new WPI_VictorSPX(DrivetrainConstants.RIGHT_MOTOR_1);
    rightMotor2 = new WPI_TalonSRX(DrivetrainConstants.RIGHT_MOTOR_2);
    rightMotor3 = new WPI_TalonSRX(DrivetrainConstants.RIGHT_MOTOR_3);

    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);
    leftMotor3.setInverted(true);

    rightMotor2.follow(rightMotor3);
    rightMotor1.follow(rightMotor3);
    leftMotor2.follow(leftMotor3);
    leftMotor1.follow(leftMotor3);

    leftMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    leftMotor3.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor3.setNeutralMode(NeutralMode.Brake);
  }

  public void drive(double leftPower, double rightPower){
    rightMotor1.set(ControlMode.PercentOutput, rightPower);
    leftMotor1.set(ControlMode.PercentOutput, leftPower);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
