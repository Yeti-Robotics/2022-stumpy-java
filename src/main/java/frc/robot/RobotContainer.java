// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.shifting.ToggleShiftCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.XboxSubsystem;
import frc.robot.utils.XController;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private DrivetrainSubsystem drivetrainSubsystem;
    private Joystick driverStationJoy;

    public ShiftSubsystem shiftSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ShooterSubsystem shooterSubsystem;
    public NeckSubsystem neckSubsystem;
    public ClimberSubsystem climberSubsystem;
    private XController xboxController;
    private boolean isDriverStation;
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */

    public RobotContainer() {
        driverStationJoy = new Joystick(Constants.OIConstants.DRIVER_STATION_JOY);
    
        drivetrainSubsystem = new DrivetrainSubsystem();
        shiftSubsystem = new ShiftSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        shooterSubsystem = new ShooterSubsystem();
        neckSubsystem = new NeckSubsystem();
        climberSubsystem = new ClimberSubsystem();

        isDriverStation = !(DriverStation.getJoystickIsXbox(0) || DriverStation.getJoystickIsXbox(1));

        switch (drivetrainSubsystem.getDriveMode()) {
            case TANK:
                drivetrainSubsystem.setDefaultCommand(
                        new RunCommand(() -> drivetrainSubsystem.tankDrive(getLeftY(), getRightY()),
                                drivetrainSubsystem));
                break;
            case CHEEZY:
                drivetrainSubsystem.setDefaultCommand(
                        new RunCommand(() -> drivetrainSubsystem.cheezyDrive(getLeftY(), getRightX()),
                                drivetrainSubsystem));
                break;
            case ARCADE:
                drivetrainSubsystem.setDefaultCommand(
                        new RunCommand(() -> drivetrainSubsystem.arcadeDrive(getLeftY(), getRightX()),
                                drivetrainSubsystem));
                break;
        }

        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        setJoystickButtonWhenPressed(driverStationJoy, 12, new ToggleShiftCommand(shiftSubsystem));

        setJoystickButtonWhileHeld(driverStationJoy, 6, new StartEndCommand(() -> climberSubsystem.climbUp(),() -> climberSubsystem.stopClimb(), climberSubsystem));
        setJoystickButtonWhileHeld(driverStationJoy, 1, new StartEndCommand(() -> climberSubsystem.climbDown(),() -> climberSubsystem.stopClimb(), climberSubsystem));


        setJoystickButtonWhenPressed(driverStationJoy, 2, new InstantCommand(() -> climberSubsystem.toggleClimberLean(), climberSubsystem));
        setJoystickButtonWhenPressed(driverStationJoy, 7, new InstantCommand(() -> climberSubsystem.toggleAirBrake(), climberSubsystem));

        setXboxButtonWhileHeld(xboxController , Button.kY, new StartEndCommand(() -> climberSubsystem.climbUp(),() -> climberSubsystem.stopClimb(), climberSubsystem));
        setXboxButtonWhileHeld(xboxController, Button.kX, new StartEndCommand(() -> climberSubsystem.climbDown(),() -> climberSubsystem.stopClimb(), climberSubsystem));

        setXboxButtonWhenPressed(xboxController, Button.kA, new InstantCommand(() -> climberSubsystem.toggleClimberLean(), climberSubsystem));
        setXboxButtonWhenPressed(xboxController, Button.kB,  new InstantCommand(() -> climberSubsystem.toggleAirBrake(), climberSubsystem));
    }

    public double getLeftY() {
        return -driverStationJoy.getRawAxis(0);
    }

    public double getLeftX() {
        return driverStationJoy.getRawAxis(1);
    }

    public double getRightY() {
        return -driverStationJoy.getRawAxis(2);
    }

    public double getRightX() {
        return -driverStationJoy.getRawAxis(3);
    }

    private void setJoystickButtonWhenPressed(Joystick driverStationJoy, int i, Command command) {
        new JoystickButton(driverStationJoy, i).whenPressed(command);
    }

    private void setJoystickButtonWhileHeld(Joystick driverStationJoy, int i, Command command) {
        new JoystickButton(driverStationJoy, i).whileHeld(command);
    }
    private void setXboxButtonWhenPressed(XboxController xboxController, Button button, CommandBase command) {
        new JoystickButton(xboxController, button.value).whenPressed(command);
    
    }
    private void setXboxButtonWhileHeld(XboxController xboxController, XboxController.Button button, CommandBase command) {
        new JoystickButton(xboxController, button.value).whileHeld(command);
      }
}
