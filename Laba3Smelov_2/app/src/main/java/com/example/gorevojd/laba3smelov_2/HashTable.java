package com.example.gorevojd.laba3smelov_2;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by GOREVOJD on 23.09.2016.
 */

public class HashTable {

    static final int startElementCount = 2;
    static final int rowByteSize = 36;
    static final int byteOffsetToOffsetValue = 30;
    static final int byteOffsetToValue = 10;
    static final int byteOffsetToNewLineSymbol = 34;

    static int elementCount = startElementCount;

    public static void MarkFile(File file){
        try{
            RandomAccessFile rafile = new RandomAccessFile(file, "rw");
            try{
                for(int j = 0; j < startElementCount; j++){
                    for(int i = 0; i < rowByteSize; i++){
                        rafile.write(0);
                    }
                }
            }
            catch(IOException e1){

            }
        }
        catch(FileNotFoundException e){

        }
    }

    public static void WritePair(PairKeyValue p, File file){
        try{
            RandomAccessFile rafile = new RandomAccessFile(file, "rw");

            int entryPoint = CalcPairHashCode(p.getKey(), startElementCount);
            int cursorTartetBytePos = entryPoint * rowByteSize;

            rafile.seek(cursorTartetBytePos);

            byte[] tempBuf = new byte[10];
            rafile.read(tempBuf);

            boolean b = true;
            while(b == true){
                //COLLISION
                if(tempBuf[0] != 0 || tempBuf[1] != 0){
                    Log.d("LAB3", "COLLISION WHILE WRITING");

                    //rafile.seek(cursorTartetBytePos);
                    String readStr = new String(tempBuf, "UTF-16");
                    boolean keysAreEqual = p.getKey().equals(readStr);
                    if(keysAreEqual == false){

                        byte[] offsetBuf = new byte[4];
                        rafile.seek(cursorTartetBytePos + byteOffsetToOffsetValue);
                        rafile.read(offsetBuf);
                        if(Arrays.equals(offsetBuf, new byte[]{0, 0, 0, 0})){
                            //WRITING OFFSET
                            rafile.seek(cursorTartetBytePos + byteOffsetToOffsetValue);
                            int offsetVal = elementCount * rowByteSize;
                            byte[] byteArray = new byte[]{
                                    (byte)(offsetVal>>24),
                                    (byte)(offsetVal>>16),
                                    (byte)(offsetVal>>8),
                                    (byte)(offsetVal)
                            };
                            rafile.write(byteArray);

                            //GO TO END
                            cursorTartetBytePos = elementCount * rowByteSize;
                            rafile.seek(cursorTartetBytePos);
                            elementCount++;

                            //NOTHING TO READ AT THE END OF FILE SO EXIT THE LOOP
                            b = false;
                        }
                        else{

                            ByteBuffer  wrapped = ByteBuffer.wrap(offsetBuf);
                            int nextKeyOffset = wrapped.getInt();
                            cursorTartetBytePos = nextKeyOffset;
                            rafile.seek(cursorTartetBytePos);
                            rafile.read(tempBuf);
                        }
                    }else{
                        //REWRITE KEY AND VALUE IF KEY IS THE SAME AS WRITTEN

                        rafile.seek(cursorTartetBytePos);
                        rafile.writeChars(p.getKey());
                        rafile.seek(cursorTartetBytePos + byteOffsetToValue);
                        rafile.writeChars(p.getValue());
                        b = false;

                        return;
                    }
                }
                else{
                    b = false;
                }
            }

            String writeKey = new String(p.getKey());
            String writeVal = new String(p.getValue());

            rafile.seek(cursorTartetBytePos);
            rafile.writeChars(writeKey);
            rafile.seek(cursorTartetBytePos + byteOffsetToValue);
            rafile.writeChars(writeVal);
            rafile.seek(cursorTartetBytePos + byteOffsetToOffsetValue);
            rafile.write(new byte[]{0, 0, 0, 0});
            rafile.seek(cursorTartetBytePos + byteOffsetToNewLineSymbol);
            rafile.writeChars("\n");

            rafile.close();
        }
        catch(IOException e){

        }
    }

    public static String RestorePair(File file, String key){

        String retVal = new String();
        try{
            RandomAccessFile rafile = new RandomAccessFile(file, "r");

            int entryPoint = CalcPairHashCode(key, startElementCount);
            int cursorTargetBytePos = entryPoint * rowByteSize;

            boolean b = true;
            while(b == true){
                byte[] readBuf = new byte[10];
                rafile.seek(cursorTargetBytePos);
                rafile.read(readBuf);
                String readStr = new String(readBuf, "UTF-16");
                //boolean keysAreEqual = Arrays.equals(readBuf, key);
                boolean keysAreEqual = readStr.equals(key);

                if(keysAreEqual == true){
                    //FOUND!!!
                    int readValueTargetBytePos = cursorTargetBytePos + byteOffsetToValue;
                    byte[] valueMass = new byte[20];
                    rafile.seek(readValueTargetBytePos);
                    rafile.read(valueMass);
                    retVal = new String(valueMass, "UTF-16");
                    b = false;
                }
                else{
                    //NOT FOUND
                    //First we need to check offset value;
                    //If it exists we need to iterate to next pair(next pair exists);
                    //Else next pair does not exists so we would need to stop iterating;

                    int offsetValueTargetBytePos = cursorTargetBytePos + byteOffsetToOffsetValue;
                    rafile.seek(offsetValueTargetBytePos);
                    byte[] offMass = new byte[4];
                    rafile.read(offMass);

                    if((offMass[0] == 0)
                            && (offMass[1] == 0)
                            && (offMass[2] == 0)
                            && (offMass[3] == 0))
                    {
                        //Next value is NOT exists
                        break;
                    }
                    else{
                        //Next value is exists
                        ByteBuffer  wrapped = ByteBuffer.wrap(offMass);
                        cursorTargetBytePos = wrapped.getInt();
                    }
                }
            }
        }
        catch(IOException e){

        }
        //TODO(Dima): Process case when we return 0 mass
        return retVal;
    }

    static int CalcPairHashCode(String key, int hashMapBeginSize){
        return key.hashCode() % hashMapBeginSize;
    }
}
