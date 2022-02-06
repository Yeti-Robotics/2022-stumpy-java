// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.LED.AuroraLEDCommand;
import frc.robot.commands.LED.BlinkLEDCommand;
import frc.robot.commands.LED.RainbowLEDCommand;
import frc.robot.commands.LED.SetLEDYetiBlueCommand;
import frc.robot.commands.LED.SnowfallLEDCommand;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.utils.XboxDPad;
import frc.robot.utils.XboxDPad.Direction;
import frc.robot.utils.XboxTrigger;
import frc.robot.utils.XboxTrigger.Hand;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private CommandScheduler commandScheduler;
    public Joystick driverStationJoystick;
    private XboxController xboxController; 
    private XboxTrigger rightTrigger; 
    private XboxTrigger leftTrigger;
    public boolean isDriverStation;

    public LEDSubsystem ledSubsystem;
    private HashMap<Integer, CommandBase> buttonMap;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        commandScheduler = CommandScheduler.getInstance();
        
        isDriverStation = !(DriverStation.getJoystickIsXbox(0) || DriverStation.getJoystickIsXbox(1)); 

        ledSubsystem = new LEDSubsystem();
        buttonMap = new HashMap<>();

        ledSubsystem.setDefaultCommand(new SetLEDYetiBlueCommand(ledSubsystem));
        
        // Configure the button bindings
        configureButtonBindings();
    }
    
    private void configureButtonBindings() {
        // POWER PORT ROBOT CONTROLS
        // setJoystickButtonWhenPressed(driverStationJoystick, 1, new TurnToTargetPIDCommand(turretSubsystem));
        // setJoystickButtonWhenPressed(driverStationJoystick, 2, new ToggleIntakePistonCommand(intakeSubsystem));
        // setJoystickButtonWhileHeld(driverStationJoystick, 3, new AllInCommand(pinchRollerSubsystem, intakeSubsystem, hopperSubsystem));
        // setJoystickButtonWhenPressed(driverStationJoystick, 4, new ToggleShooterCommand(shooterSubsystem));
        // setJoystickButtonWhileHeld(driverStationJoystick, 5, new AllOutCommand(pinchRollerSubsystem, intakeSubsystem, hopperSubsystem));
        // setJoystickButtonWhenPressed(driverStationJoystick, 6, new StopShooterCommand(shooterSubsystem));
        // setJoystickButtonWhileHeld(driverStationJoystick, 7, new TestHoodCommand(hoodSubsystem, .1));
        // setJoystickButtonWhileHeld(driverStationJoystick, 8, new TestHoodCommand(hoodSubsystem, -.1));
        // setJoystickButtonWhileHeld(driverStationJoystick, 9, new TurretTestCommand(turretSubsystem, -0.2)); //left
        // setJoystickButtonWhileHeld(driverStationJoystick, 10, new TurretTestCommand(turretSubsystem, 0.2)); //right
        // setJoystickButtonWhileHeld(driverStationJoystick, 11, new IntakeInCommand(intakeSubsystem));
            /*  
                Allowed buttons:
                kA, kB, kBack, kBumperLeft, kBumperRight, kStart, kStickLeft, kStickRight, kX, kY (and triggers)
            */
            int port = (DriverStation.getJoystickIsXbox(0)) ? 0 : 1;
            xboxController = new XboxController(port); 
            rightTrigger = new XboxTrigger(xboxController, Hand.RIGHT);
            leftTrigger = new XboxTrigger(xboxController, Hand.LEFT);
            
            setXboxButtonWhenPressed(xboxController, Button.kA, new SnowfallLEDCommand(ledSubsystem, 100));
            setXboxButtonWhenPressed(xboxController, Button.kB, new RainbowLEDCommand(ledSubsystem, 4));
            setXboxButtonWhenPressed(xboxController, Button.kY, new BlinkLEDCommand(ledSubsystem, 300, 255, 34, 0).withTimeout(5));// up
            setXboxButtonWhenPressed(xboxController, Button.kX, new AuroraLEDCommand(ledSubsystem));// down
    }

    public double getLeftY() {
        return (isDriverStation) ? -driverStationJoystick.getRawAxis(0) : -xboxController.getLeftY();
    }

    public double getLeftX() {
        return (isDriverStation) ? driverStationJoystick.getRawAxis(1) : -xboxController.getLeftX();
    }

    public double getRightY() {
        return (isDriverStation) ? -driverStationJoystick.getRawAxis(2) : -xboxController.getRightY();
    }

    public double getRightX() {
        return (isDriverStation) ? driverStationJoystick.getRawAxis(3) : -xboxController.getRightX();
    }

    public HashMap<Integer, CommandBase> getButtonMap() {
        return buttonMap;
    }

    private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
        new JoystickButton(joystick, button).whenPressed(command);
        buttonMap.put(button, command);
    }

    private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
        new JoystickButton(joystick, button).whileHeld(command);
        buttonMap.put(button, command);
    }

    // Xbox controller equivalents
    private void setXboxButtonWhenPressed(XboxController xboxController, XboxController.Button button, CommandBase command) {
        new JoystickButton(xboxController, button.value).whenPressed(command);
    }

    private void setXboxButtonWhileHeld(XboxController xboxController, XboxController.Button button, CommandBase command) {
        new JoystickButton(xboxController, button.value).whileHeld(command);
    }

    private void setXboxTriggerWhenPressed(Hand triggerSide, CommandBase command){
        if(triggerSide == Hand.LEFT){ 
            leftTrigger.whenActive(command);
        } else {
            rightTrigger.whenActive(command);
        }
    }

    private void setXboxTriggerWhileHeld(Hand triggerSide, CommandBase command){
        if(triggerSide == Hand.LEFT){ 
            leftTrigger.whileActiveContinuous(command);
        } else {
            rightTrigger.whileActiveContinuous(command);
        }
    }

    private void setXboxDPadWhenPressed(Direction direction, CommandBase command) {
        new XboxDPad(xboxController, direction).whenPressed(command);
    }

    private void setXboxDPadWhileHeld(Direction direction, CommandBase command) {
        new XboxDPad(xboxController, direction).whileHeld(command);
    }

    public void updateIsDriverStation(){
        boolean prev = isDriverStation;
        isDriverStation = !(DriverStation.getJoystickIsXbox(0) || DriverStation.getJoystickIsXbox(1));
        if (prev == isDriverStation) {
            return;
        } else {
            commandScheduler.clearButtons();
            configureButtonBindings();
        }
    }

    // public Command getAutonomousCommand() {}

    public boolean getButtonStatus(Joystick joystick, int button) {
        return driverStationJoystick.getRawButton(button);
    }
}
