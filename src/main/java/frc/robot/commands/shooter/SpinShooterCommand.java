// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class SpinShooterCommand extends PIDCommand {
  private final ShooterSubsystem shooterSubsystem;
  private final double power;
  public SpinShooterCommand(ShooterSubsystem shooterSubsystem, double power) {
    super(
      new PIDController(0.001, 0.0, 0.0),
      shooterSubsystem::getFlywheelRPM,
      ShooterConstants.MAX_RPM,
      output -> {
        // Use the output here
        System.out.println("Shooter PID Output: " + output);
        System.out.println("Flywheel RPM: " + shooterSubsystem.getFlywheelRPM());
        shooterSubsystem.shootFlywheel(0.85 + output);
      }
    );
    this.shooterSubsystem = shooterSubsystem;
    this.power = power;

    getController().setTolerance(10.0);
    addRequirements(shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterSubsystem.shootFlywheel(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.shootFlywheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
