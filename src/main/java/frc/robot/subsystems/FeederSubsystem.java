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

  private static SparkMax feederMotor = new SparkMax(12, MotorType.kBrushless);
  private static SparkMaxConfig feederMotorConfig = new SparkMaxConfig();

  public FeederSubsystem() {
    configureFeederMotor();
  }

  /** Configure motor controller parameters for the feeder motor. */
  private void configureFeederMotor() {
    feederMotorConfig.idleMode(IdleMode.kBrake);
    feederMotorConfig.smartCurrentLimit(80);

    feederMotor.configure(feederMotorConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters)  ;
  }

  public Command runFeederCommand() {
    return Commands.runOnce(() -> feederMotor.set(0.2), this);
  }

  public Command reverseFeederCommand() {
    return Commands.runOnce(() -> feederMotor.set(-0.2), this);
  }

  public Command stopFeederCommand() {
    return Commands.runOnce(() -> feederMotor.set(0), this);
  }

  public Command autoFeederCommand() {
    return Commands.sequence(
        Commands.runOnce(() -> feederMotor.set(0.2), this),
        Commands.waitSeconds(5),
        Commands.runOnce(() -> feederMotor.set(0), this));
  }
}