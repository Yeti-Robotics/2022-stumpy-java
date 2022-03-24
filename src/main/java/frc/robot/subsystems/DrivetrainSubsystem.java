// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {
    private WPI_TalonSRX rightMotor1, leftMotor1, leftMotor2;
    private WPI_VictorSPX rightMotor2, rightMotor3, leftMotor3;

    private MotorControllerGroup rightMotors;
    private MotorControllerGroup leftMotors;

    private DifferentialDrive drive;
    private DriveMode driveMode;

    public enum DriveMode {
        TANK, CHEEZY, ARCADE
    }

    public DrivetrainSubsystem() {
        rightMotor1 = new WPI_TalonSRX(DrivetrainConstants.RIGHT_MOTOR_1);
        rightMotor2 = new WPI_VictorSPX(DrivetrainConstants.RIGHT_MOTOR_2);
        rightMotor3 = new WPI_VictorSPX(DrivetrainConstants.RIGHT_MOTOR_3);
        leftMotor1 = new WPI_TalonSRX(DrivetrainConstants.LEFT_MOTOR_1);
        leftMotor2 = new WPI_TalonSRX(DrivetrainConstants.LEFT_MOTOR_2);
        leftMotor3 = new WPI_VictorSPX(DrivetrainConstants.LEFT_MOTOR_3);

        rightMotors = new MotorControllerGroup(rightMotor1, rightMotor2, rightMotor3);
        leftMotors = new MotorControllerGroup(leftMotor1, leftMotor2, leftMotor3);

        drive = new DifferentialDrive(leftMotors, rightMotors);

        rightMotors.setInverted(true);

        leftMotor1.setNeutralMode(NeutralMode.Coast);
        leftMotor2.setNeutralMode(NeutralMode.Coast);
        leftMotor3.setNeutralMode(NeutralMode.Coast);
        rightMotor1.setNeutralMode(NeutralMode.Coast);
        rightMotor2.setNeutralMode(NeutralMode.Coast);
        rightMotor3.setNeutralMode(NeutralMode.Coast);

        driveMode = DriveMode.CHEEZY;
    }

    public void tankDrive(double leftpower, double rightpower) {
        drive.tankDrive(leftpower, rightpower);
    }

    public void cheezyDrive(double straight, double turn) {
        drive.curvatureDrive(straight, turn, true);
    }

    public void arcadeDrive(double straight, double turn) {
        drive.arcadeDrive(straight, turn, true);
    }

    public void stopDrive() {
        rightMotors.set(0.0);
        leftMotors.set(0.0);
    }

    public DriveMode getDriveMode() {
        return driveMode;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
