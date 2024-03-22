// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;


public class SetShooterPitch extends Command {
  /** Creates a new driveShooterPitch. */

  public ShooterPitchSubsystem shooterPitchSubsystem;
  private CommandXboxController controlController;

  private double rotation = 0.55;

  public SetShooterPitch(ShooterPitchSubsystem pitch, CommandXboxController controller) {

    this.shooterPitchSubsystem = pitch;
    this.controlController = controller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterPitchSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   // if (Math.abs(controlController.getRawAxis(3)) >= 0.02) {
      rotation = MathUtil.clamp(((controlController.getRawAxis(3) + 1) / 3), 0.35, 0.57);
   // }

    System.out.println(rotation);
    shooterPitchSubsystem.setPitch(rotation);
    

    // if (speed >= 0) {
    //   speed = speed / 2;
    // }
    
    // shooterPitchSubsystem.drivePitch(speed / 5);
    // if (speed >= 0.2) {
    //   System.out.println(speed);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterPitchSubsystem.stopPitch();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}