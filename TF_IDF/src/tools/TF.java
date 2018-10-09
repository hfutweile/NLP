package tools;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TF {
	//计算一个文件中每个词的文档频率
	public static HashMap<String,Double> getTF (String filename){
		//用于存储文件中的所哟行文本
		ArrayList<String>lines=new ArrayList<String>();
		//用于存储每个单词的词频
		HashMap<String,Integer> wordMap=new HashMap<String,Integer>();
		//用于存储每个单词的tf
		HashMap<String,Double> tfMap=new HashMap<String,Double>();
		//所有单词数量
		int totalCount=0;
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader br = new BufferedReader(reader);
			//将文件内容全部加入到可变队列中
			String line=null;
			while((line=br.readLine())!=null) {
				lines.add(line);
			}
			//关闭文件操作
			br.close();
			reader.close();
			//计算每个单词的词频
			for(int i=0;i<lines.size();i++) {
				String []strArray=lines.get(i).split(" |、|，|。|；|？|！|,|\\.|;|\\?|!|\"|\\'|\\-|\\(|\\)|]");
				totalCount+=strArray.length;
				for(int j=0;j<strArray.length;j++) {
					if(strArray[i]==" "||strArray[i]==""||strArray[i]==null)continue;
					if(wordMap.containsKey(strArray[j])) {
						wordMap.put(strArray[j], wordMap.get(strArray[j])+1);
					}
					else {
						wordMap.put(strArray[j], 1);
					}
				}
			}
			//计算每个单词的tf
			Iterator<String> it=wordMap.keySet().iterator();
			while(it.hasNext()) {
				String key=it.next();
				tfMap.put(key,1.0*wordMap.get(key)/totalCount);
			}
		}
		//异常处理
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return tfMap;
	}
}
