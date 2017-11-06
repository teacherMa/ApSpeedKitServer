/**
 * @author teacherMa
 * Created on 2017/11/6.
 */
public class Kit {
    public static byte[] intToByteArray(int inputIntergerValue) throws Exception {
        byte[] convertedByteArr = new byte[4];
        for (int i = 0; i < convertedByteArr.length; i++) {
            convertedByteArr[i] = (byte) ((inputIntergerValue >> (8 * i)) & 0xFF);
        }

        return convertedByteArr;
    }

    public static int ByteArrayToInt(byte res[]) throws Exception {
        int convertedInteger = 0;
        for(int i = 0; i < res.length; i++){
            byte curValue = res[i];
            long shiftedValue = curValue << (i * 8);
            long mask = 0xFF << (i * 8);
            long maskedShiftedValue = shiftedValue & mask;
            convertedInteger |= maskedShiftedValue;
        }

        return convertedInteger;
    }
}
