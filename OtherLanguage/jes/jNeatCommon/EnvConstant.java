package jNeatCommon;

/**
 * Insert the type's description here.
 * Creation date: (21/03/2002 13.55.19)
 * @author: Administrator
 */
public class EnvConstant {

	// current operating system : "Windows..","Linux..."
	public static String OP_SYSTEM = " ";

	// current version of operating system
	public static String OS_VERSION = " ";

	// current directory
	public static String JNEAT_DIR = "c:\\jneat\\dati ";

	// current file name for parameter single without dir o other)
	public static String NAME_PARAMETER = "parametri";

	// current file name for parameter single without dir o other)
	public static String NAME_SESSION = "session";

	// current full data set name file parametri
	//   public static String DSN_PARAMETER = "xxx";

	// current full data set name file sessione 
	//   public static String DSN_SESSION = "xxx";

	// name of sub-directory for genome, population winner others...
	public static String DSN_DIR_DATA = "data";

	// character for composing full dsn : is a separator for this system

	public static String OS_FILE_SEP = "\\";

	// name of GENOME 1

	public static String NAME_GENOMEA = "ga";

	public static String NAME_GENOMEB = "gb";

	public static String NAME_GENOMEC = "gc";

	/**
	 * UiConstant constructor comment.
	 */
	public EnvConstant() {
		super();
	}

	//   public static String NAME_CURR_POPULATION = "xxx";

	public static String CLASS_FITNESS = "xxxxx";
	// if true can permit the compile a class....
	public static String CURRENT_CLASS = "xxx";
	public static String DATA_INP = "datain";
	public static String DATA_OUT = "dataout";
	public static int EDIT_CLASS_FIT = 1;
	public static int EDIT_CLASS_INP = 2;
	public static int EDIT_CLASS_OUT = 3;
	public static int EDIT_STATUS = 0;
	public static int NR_UNIT_INPUT = 2;
	public static int NR_UNIT_MAX = 5;
	public static int NR_UNIT_OUTPUT = 1;
	public static String PREFIX_SPECIES_FILE = "specie";
	public static String PREFIX_WINNER_FILE = "xwinner";
	public static double PROBABILITY_OF_CONNECTION = 0.2;
	public static boolean RECURSION = false; // simulazione da classe
	public static int SIMULATION_FROM_CLASS = 2; // simulazione da file
	public static int SIMULATION_FROM_FILE = 1;
	public static int START_FROM_GENOME = 1;
	public static int START_FROM_OLD_POPULATION = 3;
	public static int TYPE_OF_SIMULATION = 0;
	public static int TYPE_OF_START = 0;
	public static int NUMBER_OF_EPOCH = 0;

	public static boolean FORCE_RESTART = false; // ultima popolazione trovata
	//
	public static String NAME_CURR_POPULATION = "xxx";
	public static String REPORT_SPECIES_CODA = "";
	public static String REPORT_SPECIES_CORPO = "";
	public static String REPORT_SPECIES_TESTA = "";

//	public static int SERIAL_WINNER = 0;
	public static int START_FROM_NEW_RANDOM_POPULATION = 2;
	public static boolean STOP_EPOCH = false;
	public static int NUMBER_OF_SAMPLES = 0;
	// current organism with good fitness
	public static Object CURR_ORGANISM_CHAMPION = null;
	public static String DESCRIPTION_GENOME = "";
	public static String NAME_OF_GENOME_FOR_EXECUTION = "";
	public static double MAX_FITNESS = 0.0;
	public static double MIN_ERROR = 0.0; //public static double MAX_ERROR = 0.0;

	public static int MAX_BUFFER_LOGGER = 4096;
	public static String PREFIX_GENOME_RANDOM = "genome.rnd";
	public static int ACTIVATION_PERIOD = 0;
	public static int ACTIVATION_TIMES = 1;
	public static int AUTOMATIC = 1;
	public static String CURRENT_FILE = "xxx";
	public static int MANUAL = 2;
	public static int SERIAL_SUPER_WINNER = 0;
	public static boolean SUPER_WINNER_ = false;
	// current genome used for execution
	public static Object CURR_GENOME_RUNNING = null;	public static String CURRENT_POPULATION_VIEW = "xxx";	// 01.06.2002 : first organism winner 
	public static Object FIRST_ORGANISM_WINNER = null;	public static double MAX_WINNER_FITNESS = 0.0;	public static boolean RUNNING = false;}