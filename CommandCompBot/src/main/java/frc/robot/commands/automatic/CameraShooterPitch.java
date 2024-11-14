// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import org.opencv.photo.Photo;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PitchConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.turret.PhotonLimelightSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class CameraShooterPitch extends Command {
  /** Creates a new CameraTurretPitch. */
  ShooterPitchSubsystem shooterPitchSubsystem;
  PhotonLimelightSubsystem photonLimelightSubsystem;
  private int id;

  public CameraShooterPitch(ShooterPitchSubsystem shooterPitch, PhotonLimelightSubsystem limelight, int id) {
    this.shooterPitchSubsystem = shooterPitch;
    this.photonLimelightSubsystem = limelight;
    this.id = id;
    addRequirements(shooterPitchSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //System.out.println(photonLimelightSubsystem.getTagPitch(id));
    //shooterPitchSubsystem.setPitch(
      //min pitch is 54.4
      //min distance pitch is 17.8
      //max distance pitch is 
      //camera pitch is 
    //MathUtil.clamp(((targetDistance/105) * (PitchConstants.PITCH_MAX - PitchConstants.PITCH_MIN)), PitchConstants.PITCH_MIN, PitchConstants.PITCH_CONTROLLER_MAX)
    double tagPitch = photonLimelightSubsystem.getTagPitch(id);
    double tagDistance = 15;
    double calculatedPitch = -1;
    double shooterVelocity = 15;
    double g = 9.8;
    double targetHeight = 5;
   
    

    double tolerance = 0.1;
    double increment = Math.toRadians(0.1);
    
    //iterate through possible degrees
    for(double a = 0; a <= Math.PI/2; a += increment) {

      //calculate t to reach x = d (ignoring y)
      double t = tagDistance / (shooterVelocity * Math.cos(tagPitch));

      //calculate y at t
      double y = 2 + (shooterVelocity * Math.sin(a) * t) - (0.5 * g * t * t);
      
      //check if y satisfies target height
      if (Math.abs(y-targetHeight) <= tolerance) {
        //check if slope is positive (can shoot up into speaker)
        double dy_dt = shooterVelocity * Math.sin(a) - g * t;
        if (dy_dt >= 0) {
          calculatedPitch = a;
        }
      }
    }
    System.out.println(Math.toDegrees(calculatedPitch));

    shooterPitchSubsystem.setPitch(Math.toDegrees(calculatedPitch));



    // double scaledTagPitch = MathUtil.clamp(Math.abs(((tagPitch - PitchConstants.MAX_DISTANCE_TARGET_ANGLE) / (PitchConstants.MAX_DISTANCE_TARGET_ANGLE - PitchConstants.MIN_DISTANCE_TARGET_ANGLE))), 0, 1);
  
    // double desiredPitch = 
    //  (1 - scaledTagPitch) * (PitchConstants.MAX_DISTANCE_SHOOTER_ANGLE - PitchConstants.MIN_DISTANCE_SHOOTER_ANGLE)
    // ;
    // //System.out.println(tagPitchDecimal);
    // //System.out.println(photonLimelightSubsystem.getTagPitch(id));
    // shooterPitchSubsystem.setPitch(PitchConstants.PITCH_MIN + desiredPitch);
    // //);
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
