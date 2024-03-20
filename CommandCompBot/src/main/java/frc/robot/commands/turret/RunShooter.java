// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

public class RunShooter extends Command {
  /** Creates a new RunShooter. */

  private ShooterSubsystem shooterSubsystem;
  private IndexerSubsystem indexerSubsystem;

  public RunShooter(ShooterSubsystem shooter) {
    this.shooterSubsystem = shooter;
    addRequirements(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterSubsystem.shoot(TurretConstants.SHOOTER_LEFT_SPEED, TurretConstants.SHOOTER_RIGHT_SPEED);
    
    System.out.println("fire in the hole");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
