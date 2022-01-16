package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ShiftSubsystem;


public class ToggleShiftCommand extends CommandBase {
    private final ShiftSubsystem shiftSubsystem;

    public ToggleShiftCommand(ShiftSubsystem shiftSubsystem) {
        this.shiftSubsystem = shiftSubsystem;
        addRequirements(shiftSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (ShiftSubsystem.getShifterPosition() == ShiftSubsystem.ShiftStatus.HIGH) {
            shiftSubsystem.shiftDown();
        } else {
            shiftSubsystem.shiftUp();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}