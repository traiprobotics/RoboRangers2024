// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.PitchConstants;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;


public class SetShooterPitch extends Command {
  /** Creates a new driveShooterPitch. */

  public ShooterPitchSubsystem shooterPitchSubsystem;
  private CommandXboxController controlController;

  private double rotation = PitchConstants.PITCH_MIN;

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

    double joystickOutput = MathUtil.clamp(-controlController.getRawAxis(3), 0, 1);
    double range = PitchConstants.PITCH_MAX - PitchConstants.PITCH_MIN;
    rotation = PitchConstants.PITCH_MIN + (joystickOutput * range);
    //translates 0 to -1 (middle to top) on joystick to values within the min and max of the pitch range
    //this should basically just hold the position at PITCH_MAX unless the joystick is moved, and the pitch will change proportional to the joystick

    //System.out.println(rotation); ////////////////////////////////////////////////////////////////// PRINT
    shooterPitchSubsystem.setPitch(rotation);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //shooterPitchSubsystem.stopPitch();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
