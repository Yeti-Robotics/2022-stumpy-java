// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AllInCommand;
import frc.robot.commands.intake.IntakeStopCommand;
import frc.robot.commands.intake.TestIntakeCommand;
import frc.robot.commands.neck.RollToBottom;
import frc.robot.commands.neck.RollToTop;
import frc.robot.commands.shifting.ToggleShiftCommand;
import frc.robot.commands.shooter.SpinShooterCommand;
import frc.robot.commands.shooter.TestShooterCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private DrivetrainSubsystem drivetrainSubsystem;
  private Joystick driverStationJoy;

  public ShiftSubsystem shiftSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private ShooterSubsystem shooterSubsystem;
  public NeckSubsystem neckSubsystem;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driverStationJoy = new Joystick(0);
    
    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.drive(getLeftY(), getRightY()), drivetrainSubsystem));
    
    shiftSubsystem = new ShiftSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    neckSubsystem = new NeckSubsystem();

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

      setJoystickButtonWhileHeld(driverStationJoy, 2, new TestIntakeCommand(intakeSubsystem, 0.5));
       setJoystickButtonWhileHeld(driverStationJoy, 7, new TestIntakeCommand(intakeSubsystem, -0.5));
        setJoystickButtonWhileHeld(driverStationJoy, 1,  new RollToTop(neckSubsystem, intakeSubsystem));
        setJoystickButtonWhileHeld(driverStationJoy, 6, new AllInCommand(neckSubsystem, intakeSubsystem));
        setJoystickButtonWhenPressed(driverStationJoy, 3, new IntakeStopCommand(intakeSubsystem));
        // setJoystickButtonWhileHeld(driverStationJoy, 3, new RollToTop(neckSubsystem,intakeSubsystem));
      // setJoystickButtonWhileHeld(driverStationJoy, 8, new RollToBottom(neckSubsystem));

      // setJoystickButtonWhenPressed(driverStationJoy, 11, new ToggleShiftCommand(shiftSubsystem));

      setJoystickButtonWhileHeld(driverStationJoy, 5, new TestShooterCommand(shooterSubsystem));
      // setJoystickButtonWhileHeld(driverStationJoy, 7, new SpinShooterCommand(shooterSubsystem, -.7));
  }
  
  
  public double getLeftY() {
    if(driverStationJoy.getRawAxis(0) >= .1 || driverStationJoy.getRawAxis(0) <= -.1) {
        return driverStationJoy.getRawAxis(0);
    } else {
        return 0;
    }
  }
  public double getRightY() {
    if(driverStationJoy.getRawAxis(2) >= .1 || driverStationJoy.getRawAxis(2) <= -.1) {
        return driverStationJoy.getRawAxis(2);
    } else {
        return 0;
    }
  }
  private void setJoystickButtonWhenPressed(Joystick driverStationJoy, int i, Command command) {
    new JoystickButton(driverStationJoy, i).whenPressed(command);
  }
  private void setJoystickButtonWhileHeld(Joystick driverStationJoy, int i, Command command) {
    new JoystickButton(driverStationJoy, i).whileHeld(command);
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
