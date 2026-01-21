package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {

  // Motor configuration for the intakeShooter subsystem
  private static SparkMax feederMotor =
      new SparkMax(12, MotorType.kBrushless); // sets cam ID 12 and type for the shooter motor
  private static SparkMaxConfig feederMotorConfig = new SparkMaxConfig();

  public static void configurefeederMotor() {
    feederMotorConfig.idleMode(IdleMode.kBrake).smartCurrentLimit(80);

    feederMotor.configure(
        feederMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public FeederSubsystem() {
    configureFeederMotor();
  }

  private void configureFeederMotor() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'configureIntakeMotor'");
  }

  public Command runFeederCommand() {
    return Commands.runOnce(
        () -> feederMotor.set(.2), this); // .4 is the speed the feeder will spin.
  }

  public Command reverseFeederCommand() {
    return Commands.runOnce(
        () -> feederMotor.set(-.2), this); // This spins the feeder motor backwards.
  }

  public Command stopFeederCommand() {
    return Commands.runOnce(() -> feederMotor.set(0), this); // stops the feeder motor
  }

  public Command autoFeederCommand() {
    return Commands.sequence(
        Commands.runOnce(() -> feederMotor.set(0.2), this), // Start feeder at 20% speed
        Commands.waitSeconds(5), // Wait for 5.0 seconds
        Commands.runOnce(() -> feederMotor.set(0), this));
  }
}
