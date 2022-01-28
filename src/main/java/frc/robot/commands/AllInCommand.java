// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AllInCommand extends CommandBase {
 private final IntakeSubsystem intakeSubsystem;
 private final NeckSubsystem neckSubsystem;

 public AllInCommand(NeckSubsystem neckSubsystem, IntakeSubsystem intakeSubsystem) {
  this.neckSubsystem = neckSubsystem;
  this.intakeSubsystem = intakeSubsystem;
  addRequirements(neckSubsystem, intakeSubsystem); 
 }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    neckSubsystem.spinNeck(0.3);
    intakeSubsystem.intakeIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    neckSubsystem.stopNeck();
    intakeSubsystem.intakeStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
