package dbfunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import datamodels.ShoppingItemModel;
import android.content.Context;
import android.util.Log;

public class FileHandler {
	private Context mContext;

	public FileHandler(Context x){
		this.mContext = x;
	}
	
	public void deleteList() throws IOException{
		//deletes all files
		File file = mContext.getFilesDir();
		file.delete();		
	}
	
	public void savelist(String fileName, List<ShoppingItemModel> lister) throws IOException{
		List<ShoppingItemModel> list = lister;
		String saveList = "";
		for(int i = 0; i < list.size(); i++){
			saveList += list.get(i).getItemName()+";"+list.get(i).getQuantity()+";";
		}
		Writer write = null;
		try {
			OutputStream out = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			write = new OutputStreamWriter(out);
			write.write(saveList);
		} finally{
			if(write !=null)
					write.close();
		}
	
	}
	
	public String loadList(String fileName){
		String temp = "";
		BufferedReader reader = null;
		try{
			InputStream fIn = mContext.openFileInput(fileName);
	        if(fIn != null){
	        	InputStreamReader inputStreamReader = new InputStreamReader(fIn);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                
                    stringBuilder.append(receiveString);
                }
                 
                fIn.close();
                temp = stringBuilder.toString();
	        }
		}
        catch (FileNotFoundException e) {
            Log.e(null, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(null, "Can not read file: " + e.toString());
        }
		
		return temp;
	}
}


