// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class TestShooterCommand extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private double power;

  public TestShooterCommand(ShooterSubsystem shooterSubsystem, double power) {
    this.shooterSubsystem = shooterSubsystem;
    this.power = power;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooterSubsystem.shootFlywheel(power);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopFlywheel();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
