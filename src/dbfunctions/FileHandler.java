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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			System.out.println("Attempting to save: "+fileName);
			OutputStream out = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			write = new OutputStreamWriter(out);
			write.write(saveList);
		} finally{
			if(write !=null)
					write.close();
		}
		
	}
	
	public void saveListNames(String listName) throws IOException{
		ArrayList<String> tempListNames = new ArrayList<String>();
		String listOfNames = "";
		Writer write = null;
		String fileForStoringListName = "FileForStoringListName";
		if(loadList(fileForStoringListName).equals("")){
			try {
				OutputStream out = mContext.openFileOutput(fileForStoringListName, Context.MODE_PRIVATE);
				write = new OutputStreamWriter(out);
				write.write(listName+",");
			} finally{
				if(write !=null)
					write.close();
			}
		} else{
			if(!loadList(fileForStoringListName).isEmpty()){
				Scanner scan = new Scanner(loadList(fileForStoringListName)).useDelimiter(",");
				while(scan.hasNext()){
					tempListNames.add(scan.next());
				}
				tempListNames.add(listName);
				for(String s : tempListNames){
					listOfNames += s+",";
				}
				try {
					OutputStream out = mContext.openFileOutput(fileForStoringListName, Context.MODE_PRIVATE);
					write = new OutputStreamWriter(out);
					write.write(listOfNames);
				} finally{
					if(write !=null)
						write.close();
				}
			}
		}
	}
	
	public String loadList(String fileName) {
		String temp = "";
		BufferedReader reader = null;
		try{
			System.out.println("Attempting to load: "+fileName);
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


