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

public class IntakeShooterSubsystem extends SubsystemBase {

  // Motor configuration for the intakeShooter subsystem
  private static SparkMax intakeShooterMotor =
      new SparkMax(13, MotorType.kBrushless); // sets cam ID 13 and type for the shooter motor
  private static SparkMaxConfig intakeShooterMotorConfig = new SparkMaxConfig();

  public IntakeShooterSubsystem() {
    configureIntakeShooterMotor();
  }

  /** Configure motor controller parameters for the intake/shooter motor. */
  private void configureIntakeShooterMotor() {
    intakeShooterMotorConfig.idleMode(IdleMode.kBrake).smartCurrentLimit(80);

    intakeShooterMotor.configure(
        intakeShooterMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    intakeShooterMotorConfig.idleMode(IdleMode.kBrake).smartCurrentLimit(80);

    intakeShooterMotor.configure(
        intakeShooterMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public Command runIntakeShooterCommand() {
    return Commands.runOnce(
        () -> intakeShooterMotor.set(.6), this); // .4 is the speed the intake will spin.
  }

  public Command reverseIntakeShooterCommand() {
    return Commands.runOnce(
        () -> intakeShooterMotor.set(-.4), this); // This spins the intake motor backwards.
  }

  public Command stopIntakeShooterCommand() {
    return Commands.runOnce(() -> intakeShooterMotor.set(0), this); // stops the intake motor
  }

  public Command autoIntakeShooterCommand() {
    return Commands.sequence(
        Commands.runOnce(() -> intakeShooterMotor.set(0.6), this), // Start intake at 50% speed
        Commands.waitSeconds(6), // Wait for 3.0 seconds
        Commands.runOnce(() -> intakeShooterMotor.set(0), this));
  }
}
