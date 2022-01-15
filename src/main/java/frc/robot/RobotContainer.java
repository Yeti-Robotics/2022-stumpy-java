// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private DrivetrainSubsystem drivetrainSubsystem;
  private Joystick driverstationJoy;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driverstationJoy = new Joystick(0);
    
    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.drive(getLeftY(), getRightY()), drivetrainSubsystem));
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  public double getLeftY() {
    if(driverstationJoy.getRawAxis(0) >= .1 || driverstationJoy.getRawAxis(0) <= -.1) {
        return driverstationJoy.getRawAxis(0);
    } else {
        return 0;
    }
  }
  public double getRightY() {
    if(driverstationJoy.getRawAxis(2) >= .1 || driverstationJoy.getRawAxis(2) <= -.1) {
        return driverstationJoy.getRawAxis(2);
    } else {
        return 0;
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
