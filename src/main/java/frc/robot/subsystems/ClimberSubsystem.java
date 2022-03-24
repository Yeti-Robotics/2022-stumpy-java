// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private WPI_TalonFX climberMotor1, climberMotor2;
    private DoubleSolenoid climberLeanPiston;
    private DoubleSolenoid climberAirBrake;

    public ClimberSubsystem() {
        climberMotor1 = new WPI_TalonFX(ClimberConstants.CLIMBER_MOTOR_1);
        climberMotor2 = new WPI_TalonFX(ClimberConstants.CLIMBER_MOTOR_2);

        climberLeanPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
                ClimberConstants.LEAN_PISTON_SOLENOID[0], ClimberConstants.LEAN_PISTON_SOLENOID[1]);
        climberAirBrake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
                ClimberConstants.AIR_BRAKE_SOLENOID[0], ClimberConstants.AIR_BRAKE_SOLENOID[1]);

        climberLeanPiston.set(Value.kReverse);
        climberAirBrake.set(Value.kReverse);

        climberMotor2.follow(climberMotor1);
        climberMotor2.setInverted(InvertType.FollowMaster);

        climberMotor1.setNeutralMode(NeutralMode.Brake);
        climberMotor2.setNeutralMode(NeutralMode.Brake);

        climberMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        climberMotor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    }

    public void climbUp() {
        if (climberAirBrake.get() == Value.kReverse && !atEncoderLimit()) {
          climberMotor1.set(0.3);
        } else {
            stopClimb();
        }
    }    
    
    public void climbDown() {
        if (climberAirBrake.get() == Value.kReverse && !atEncoderLimit()) {
            climberMotor1.set(0.3);
        } else {
            stopClimb();
        }
    }

    public void stopClimb() {
        climberMotor1.set(0.0);
    }

    public double getRightEncoderPosition() {
        return climberMotor1.getSelectedSensorPosition();
    }

    public double getLeftEncoderPostion() {
        return climberMotor2.getSelectedSensorPosition();
    }

    public double getAverageEncoderPosition() {
        return (getRightEncoderPosition() + getLeftEncoderPostion()) / 2;
    }

    public void resetEncoders() {
        climberMotor1.setSelectedSensorPosition(0.0);
        climberMotor2.setSelectedSensorPosition(0.0);
    }

    public boolean atEncoderLimit() {
        return getAverageEncoderPosition() <= ClimberConstants.CLIMBER_TOLERANCE || getAverageEncoderPosition()
            + ClimberConstants.CLIMBER_TOLERANCE >= ClimberConstants.CLIMBER_UPPER_LIMIT;
      }

    public void toggleClimberLean() {
        climberLeanPiston.toggle();
    }

    public void toggleAirBrake() {
        climberAirBrake.toggle();
    }
}
