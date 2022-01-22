// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static class DrivetrainConstants {
        public final static int LEFT_MOTOR_1 = 13;
        public final static int LEFT_MOTOR_2 = 16;
        public final static int LEFT_MOTOR_3 = 7;
        public final static int RIGHT_MOTOR_1 = 3;
        public final static int RIGHT_MOTOR_2 = 8;
        public final static int RIGHT_MOTOR_3 = 6;
    
        public static final int[] SHIFTER_SOLENOID = {6,7};
    }

    public final static class IntakeConstants {

        public final static int INTAKE_FALCON = 19; //placeholder
        public final static int[] INTAKE_PISTONS_SOLENOID = {0, 1};//placeholder

        public final static double ROLL_IN_SPEED = 0.2;
        public final static double ROLL_OUT_SPEED = -0.2;

    }

    public final static class   ShooterConstants {
        public final static int LEFT_SHOOTER_MOTOR = 18;
        public final static int RIGHT_SHOOTER_MOTOR = 1;
        public final static double REVERSE_SHOOT_SPEED = -1.0;

        public final static double MAX_RPM = 6500.0;
        public static final double ENCODER_RESOLUTION = 2048.0;
        public static final double PULLEY_RATIO = 48.0 / 36.0;
        public static final double ENCODER_TIME_CONVERSION = 600.0; // minutes per 100 ms


    }
    public final static class NeckConstants {
        public static final int NECK_MOTOR_FRONT = 17; //placeholder
        public static final int NECK_MOTOR_BACK = 15;//PLACEHOLDER   `
        public static final int NECK_BEAMBREAK = 0;//placeholder

    }


    public static final class CalcConstants{
        //distance calc constants
        public static final double KNOWN_DISTANCE = 134.2; //inches
        public static final int PIXEL_WIDTH_KNOWN = 8; //pixels (This is a placeholder value)
        public static final double KNOWN_TAPE_BOUND_WIDTH = 5; //inches
        public static final double FOCAL_LENGTH = ( KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH;
        //trajectory constants
        public static final double GRAVITY = 386.09; // inches/ sec ^2
        public static final double SHOOTER_HEIGHT = 34; // inches
    }


}
