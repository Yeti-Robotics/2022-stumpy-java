// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;


public class ShooterSubsystem extends SubsystemBase {
    private WPI_TalonFX rightFlywheel;
    private WPI_TalonFX leftFlywheel;
    
    public enum ShooterStatus {
        FORWARDS, BACKWARDS, OFF;
    }

    public static ShooterStatus shooterStatus;

    public ShooterSubsystem() {
        rightFlywheel = new WPI_TalonFX(ShooterConstants.RIGHT_SHOOTER_MOTOR);
        leftFlywheel = new WPI_TalonFX(ShooterConstants.LEFT_SHOOTER_MOTOR);

        rightFlywheel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        leftFlywheel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        
        leftFlywheel.follow(rightFlywheel);
        leftFlywheel.setInverted(InvertType.OpposeMaster);
    
        shooterStatus = ShooterStatus.OFF;

        leftFlywheel.setNeutralMode(NeutralMode.Coast);
        rightFlywheel.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void periodic(){}

    public void shootFlywheel(double speed) {
        rightFlywheel.set(ControlMode.PercentOutput, speed);
        shooterStatus = ShooterStatus.FORWARDS;
    }

    public void stopFlywheel() {
        rightFlywheel.set(ControlMode.PercentOutput, 0.0);
        shooterStatus = ShooterStatus.OFF;
    }

    public double getLeftEncoder() {
        return leftFlywheel.getSelectedSensorVelocity();
    }

    public double getRightEncoder() {
        return rightFlywheel.getSelectedSensorVelocity();
    }

    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    public static ShooterStatus getShooterStatus() {
        return shooterStatus;
    }

    public double getSetSpeed() {
        return rightFlywheel.getMotorOutputPercent();
    }

    public double getFlywheelRPM() {
        return getAverageEncoder() * ShooterConstants.PULLEY_RATIO * (ShooterConstants.ENCODER_TIME_CONVERSION / ShooterConstants.ENCODER_RESOLUTION);
    }

    public double getVelocityUnitsFromRPM(double RPM){
        return RPM / (ShooterConstants.PULLEY_RATIO * (ShooterConstants.ENCODER_TIME_CONVERSION / ShooterConstants.ENCODER_RESOLUTION));
    }
}