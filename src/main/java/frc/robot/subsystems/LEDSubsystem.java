// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class LEDSubsystem extends SubsystemBase {
  private AddressableLED ledStr;
  private static  AddressableLEDBuffer ledBuffer;
  public LEDSubsystem() {
    ledStr = new AddressableLED(LEDConstants.Addressable_LED);
    ledBuffer = new AddressableLEDBuffer(LEDConstants.LED_Count);
  }

  public static void setHSV(int i, int hue, int sat, int value){
    ledBuffer.setHSV(i, hue, sat, value);
  }

  public void setRGB(int i, int red, int green, int blue){
    ledBuffer.setRGB(i, red, green, blue);
  }
  
  public static int getBufferLength(){
    return ledBuffer.getLength();
  }

  public void sendData(){
    ledStr.setData(ledBuffer);
  }

}
