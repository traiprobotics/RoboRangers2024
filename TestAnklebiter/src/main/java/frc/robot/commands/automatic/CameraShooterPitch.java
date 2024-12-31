// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.turret.PhotonLimelightSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;

public class CameraShooterPitch extends Command {
  /** Creates a new CameraTurretPitch. */
  ShooterPitchSubsystem shooterPitchSubsystem;
  PhotonLimelightSubsystem photonLimelightSubsystem;
  private int id;

  double calculatedPitch = 0;

  public CameraShooterPitch(ShooterPitchSubsystem shooterPitch, PhotonLimelightSubsystem limelight, int id) {
    this.shooterPitchSubsystem = shooterPitch;
    this.photonLimelightSubsystem = limelight;
    this.id = id;
    addRequirements(shooterPitchSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public static double findAngleToHitTarget(double v, double g, double d, double targetY, double min, double max) {
    double tolerance = 0.05; // Tolerance for hitting the target
    //double increment = Math.toRadians(0.1); // Angle increment in radians for search
    
    if (Math.abs(min - max) <= 0.01) {
      return -1;
    }
    
    double target = min + ((max - min) / 2);
    double finalAnswer = 0;
    
    double newMin = min;
    double newMax = max;
    
    System.out.println("Searching between " + min + " and " + max + " with target " + target);

    // Search from 0 to 90 degrees
    //for (double a = 0; a <= Math.PI / 2; a += increment) {
        
    double t = d / (v * Math.cos(target)); // Calculate time to reach x = d

    // Calculate y position at this time t
    double y = 2 + (v * Math.sin(target) * t) - (0.5 * g * t * t);

    // Check if this y is close enough to the target y with non-negative slope
    if (Math.abs(y - targetY) <= tolerance) {
      double dy_dt = v * Math.sin(target) - g * t; // Vertical velocity at time t
      if (dy_dt >= 0) { // Ensure slope is non-negative
        System.out.println("Target found at " + target);
        return target; // Return angle in radians
      } else {
        newMax = target;
        System.out.println("Y hits target at negative slope");
        finalAnswer = findAngleToHitTarget(v, g, d, targetY, newMin, newMax);
      }
    } else {
      if (y - targetY >= 0) {
        newMax = target;
      System.out.println("Y is " + y + " which is greater than target");
        finalAnswer = findAngleToHitTarget(v, g, d, targetY, newMin, newMax);
      } else {
        newMin = target;
        System.out.println("Y is " + y + "which is less than target");
        finalAnswer = findAngleToHitTarget(v, g, d, targetY, newMin, newMax);
      }
    }
    return finalAnswer;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    double v = 25; // Example initial velocity in m/s
    double g = 9.8; // Gravity in m/s^2

    //need to do some testing to convert area and pitch to distance and y so this is broken rn
    double d = 15; // Distance to the target in meters
    double targetY = 5; // Height of the target in meters
        
    double min = 0;
    double max = Math.PI/2;

    double output = findAngleToHitTarget(v, g, d, targetY, min, max);

    if (output != -1) {
      System.out.printf("Angle to hit target at (%.2f, %.2f): %.2f degrees\n", d, targetY, Math.toDegrees(output));
      calculatedPitch = output;
    } else {
      System.out.println("No angle found to hit the target with the desired conditions.");
    }
  }

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
    // double tagPitch = photonLimelightSubsystem.getTagPitch(id);
    // double tagDistance = 15;
    // double calculatedPitch = -1;
    // double shooterVelocity = 15;
    // double g = 9.8;
    // double targetHeight = 5;
   
    

    // double tolerance = 0.1;
    // double increment = Math.toRadians(0.1);
    
    // //iterate through possible degrees
    // for(double a = 0; a <= Math.PI/2; a += increment) {

    //   //calculate t to reach x = d (ignoring y)
    //   double t = tagDistance / (shooterVelocity * Math.cos(tagPitch));

    //   //calculate y at t
    //   double y = 2 + (shooterVelocity * Math.sin(a) * t) - (0.5 * g * t * t);
      
    //   //check if y satisfies target height
    //   if (Math.abs(y-targetHeight) <= tolerance) {
    //     //check if slope is positive (can shoot up into speaker)
    //     double dy_dt = shooterVelocity * Math.sin(a) - g * t;
    //     if (dy_dt >= 0) {
    //       calculatedPitch = a;
    //     }
    //   }
    // }
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
