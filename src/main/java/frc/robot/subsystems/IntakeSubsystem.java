package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  public enum IntakeStatus{
    DOWN, UP
  }
  public static IntakeStatus intakeStatus;

  private final DoubleSolenoid intakePistons;
  private WPI_TalonFX intakeFalcon;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    intakeFalcon = new WPI_TalonFX(IntakeConstants.INTAKE_FALCON);
    intakePistons = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.INTAKE_PISTONS_SOLENOID[0], IntakeConstants.INTAKE_PISTONS_SOLENOID[1]);

    intakeFalcon.setInverted(true);
    intakeStatus = IntakeStatus.DOWN;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void extend(){
    intakePistons.set(DoubleSolenoid.Value.kForward);
    intakeStatus = IntakeStatus.DOWN;
  }
  public void retract(){
      intakePistons.set(DoubleSolenoid.Value.kReverse);
      intakeStatus = IntakeStatus.UP;
  }
  
  public void intakeIn(){
      intakeFalcon.set(ControlMode.PercentOutput, IntakeConstants.ROLL_IN_SPEED);
  }
  public void intakeOut(){
      intakeFalcon.set(ControlMode.PercentOutput, IntakeConstants.ROLL_OUT_SPEED);
  }
  public void intakeStop(){
      intakeFalcon.set(ControlMode.PercentOutput, 0);
  }

  public void intakeSpin(double power) {
      intakeFalcon.set(ControlMode.PercentOutput, power);
  }
  public IntakeStatus getIntakePosition(){
      return intakeStatus;
  }

  public WPI_TalonFX getintakeFalcon(){
    return intakeFalcon;
  }
}
