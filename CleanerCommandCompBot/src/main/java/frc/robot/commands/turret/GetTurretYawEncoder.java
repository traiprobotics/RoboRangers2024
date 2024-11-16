// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class GetTurretYawEncoder extends Command {
  /** Creates a new GetTurretYawEncoder. */

  private TurretYawSubsystem turretYawSubsystem;

  public GetTurretYawEncoder(TurretYawSubsystem turretYaw) {
    this.turretYawSubsystem = turretYaw;
    addRequirements(turretYawSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(turretYawSubsystem.getTurretYaw());
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
