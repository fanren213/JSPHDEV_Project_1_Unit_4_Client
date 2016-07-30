/**
 * @author      Bonan Cao <bonanc@andrew.cmu.edu>
 * @Andrew ID   bonanc
 * @Date 		Sept. 23. 2015
 */
package exception;
public enum ExceptionEnum{
	PRICE_MISSING(1, "Base price is missing."),
	OPTIONSET_DATA_MISSING(2, "Option set with given name is missing."),
	OPTION_DATA_MISSING(3,"Option with given name is missing"),
	FILENAME_ERROR(4,"Error in the input filename."),
	OPTIONSET_EXISTED(5,"Option set with same name already existed."),
	OPTION_EXISTED(6,"Option with same name already existed."),
	IOEXCEPTION_IN_FILE(7,"IO exception appeared when reading file."),
	NUMBER_FORMAT_ERROR(8,"The format of number in the file is wrong."),
	CLASS_NOT_FOUND_EXCEPTION(9,"Certain class not found in deserialization."),
	CAR_MODEL_MISSING(10,"Model with given name is missing.");
	private int errorNo;
	private String errorMsg;
	private ExceptionEnum(int errorNo, String errorMsg){
		this.errorNo = errorNo;
		this.errorMsg = errorMsg;
	}
	public static String getErrorMsg(int errorNo){
		for (ExceptionEnum c : ExceptionEnum.values()) {
            if (c.getErrorNo() == errorNo) {
                return c.errorMsg;
            }
        }
		return null;
	}
	public void setErrorNo(int errorNo){
		this.errorNo = errorNo;
	}
	public int getErrorNo(){
		return errorNo;
	}
	public void setErrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}
	public String getErrorMsg(){
		return errorMsg;
	}
}
