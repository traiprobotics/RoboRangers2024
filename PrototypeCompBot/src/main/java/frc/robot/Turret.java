package frc.robot;

import javax.print.CancelablePrintJob;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;

public class Turret {
    private static CANSparkMax indexer;
    private static CANSparkMax shooterLeft;
    private static CANSparkMax shooterRight;

    private static CANSparkMax shooterPitch;
    private static CANSparkMax turretYaw;

      private static final SparkMaxAlternateEncoder.Type kAltEncType = SparkMaxAlternateEncoder.Type.kQuadrature;
  private static final int kCPR = 8192;

    //private static AbsoluteEncoder pitchEncoder;

   private SparkPIDController m_pidController;
   public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

   private RelativeEncoder m_alternateEncoder;
    //private AbsoluteEncoder m_absolute;


public Turret(){
    shooterPitch = new CANSparkMax(8,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    indexer = new CANSparkMax(9,com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);
    shooterLeft = new CANSparkMax(10,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    shooterRight = new CANSparkMax(11,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    turretYaw = new CANSparkMax(7,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

    m_alternateEncoder = shooterPitch.getAlternateEncoder(kAltEncType, kCPR);
    m_alternateEncoder.setInverted(false);

    m_pidController = shooterPitch.getPIDController();
    m_pidController.setFeedbackDevice(m_alternateEncoder);

    //m_absolute = shooterPitch.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);



// shooterPitch.setFeedbackDevice(pitchEncoder);

    
}

public double getShooterPitch() {
    //return shooterPitch.getAbsoluteEncoder().getPosition();
    return m_alternateEncoder.getPosition();
}

public static void driveShooterPitch(double speed) {
    shooterPitch.set(speed);
}

public static void shooterPitchStop() {
    shooterPitch.stopMotor();
}

public static void indexIn() {
    indexer.set(-1.0f);  
}

public static void shoot( double leftSpeed, double rightSpeed) {
    System.out.println("fire in the hole!");
    shooterLeft.set(leftSpeed);
    shooterRight.set(-rightSpeed);
}

public static void shooterStop() {
    shooterLeft.stopMotor();
    shooterRight.stopMotor();
}

public static void indexerStop() {
    indexer.stopMotor();
}

public static void rotateTurret(double speed) {
    turretYaw.set(speed);
}

public static void stopTurret() {
    turretYaw.stopMotor();
}


    
}
