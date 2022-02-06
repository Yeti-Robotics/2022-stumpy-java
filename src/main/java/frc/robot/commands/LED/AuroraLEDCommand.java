// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LEDSubsystem;

public class AuroraLEDCommand extends CommandBase {
  /** Creates a new AuroraLEDCommand. */
  private LEDSubsystem ledSubsystem;

  // HSV
  private int[] mintGreen = {30, 222, 32};
  private int mintGreenPinkBoundary = LEDConstants.LED_COUNT / 8;

  private int[] pink = {199, 68, 235};
  private int pinkLightBlueBoundary = LEDConstants.LED_COUNT / 4;

  private int[] lightBlue = {4, 255, 219};
  private int lightBlueDarkBlueBoundary = (3 * LEDConstants.LED_COUNT) / 8;

  private int[] darkBlue = {62, 0, 216};
  private int darkBlueMintGreenBoundary = LEDConstants.LED_COUNT / 2;

  private int position = 0;

  private int gradientLength = 4;
  private long startTime = System.currentTimeMillis();
  private int waitTime = 50;

  public AuroraLEDCommand(LEDSubsystem ledSubsystem) {
    this.ledSubsystem = ledSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ledSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  private int wrapValues(int num) {
    return num % LEDConstants.LED_COUNT;
  }

  private int[] getAvgValue(int[] color1, int[] color2) {
    return new int[] {(color1[0] + color2[0]) / 2, (color1[1] + color2[1]) / 2, (color1[2] + color2[2]) / 2};
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (System.currentTimeMillis() - startTime >= waitTime) {
      for (int j = 0; j < (LEDConstants.LED_COUNT / 2) + 1; j += (LEDConstants.LED_COUNT / 2)) {
        int i = 0;
        for (i = i + j + position; i < (mintGreenPinkBoundary - gradientLength) + j + position; i++) {
          ledSubsystem.setRGB(wrapValues(i), mintGreen[0], mintGreen[1], mintGreen[2]);
        }
        for (i = i; i < mintGreenPinkBoundary + j + position; i++) {
          int[] avgColor = getAvgValue(mintGreen, lightBlue);
          ledSubsystem.setRGB(wrapValues(i), avgColor[0], avgColor[1], avgColor[2]);
        }
        for (i = i; i < (pinkLightBlueBoundary - gradientLength) + j + position; i++) {
          ledSubsystem.setRGB(wrapValues(i), pink[0], pink[1], pink[2]);
        }
        for (i = i; i < pinkLightBlueBoundary + j + position; i++) {
          int[] avgColor = getAvgValue(lightBlue, pink);
          ledSubsystem.setRGB(wrapValues(i), avgColor[0], avgColor[1], avgColor[2]);
        }
        for (i = i; i < (lightBlueDarkBlueBoundary - gradientLength) + j + position; i++) {
          ledSubsystem.setRGB(wrapValues(i), lightBlue[0], lightBlue[1], lightBlue[2]);
        }
        for (i = i; i < lightBlueDarkBlueBoundary + j + position; i++) {
          int[] avgColor = getAvgValue(lightBlue, darkBlue);
          ledSubsystem.setRGB(wrapValues(i), avgColor[0], avgColor[1], avgColor[2]);
        }
        for (i = i; i < (darkBlueMintGreenBoundary - gradientLength) + j + position; i++) {
          ledSubsystem.setRGB(wrapValues(i), darkBlue[0], darkBlue[1], darkBlue[2]);
        }
        for (i = i; i < darkBlueMintGreenBoundary + j + position; i++) {
          int[] avgColor = getAvgValue(darkBlue, mintGreen);
          ledSubsystem.setRGB(wrapValues(i), avgColor[0], avgColor[1], avgColor[2]);
        }
      }
      ledSubsystem.sendData();
      position = wrapValues(position + 1);
      startTime = System.currentTimeMillis();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
