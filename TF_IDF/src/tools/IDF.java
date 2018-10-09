package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class IDF {
	//训练文件的数量
	public static int TOTALDOC=0;
	//计算所有词的逆向文档频率
	public static HashMap<String,Double> getIDF(String dir){
		//返回的所有词的逆文档频率
		HashMap<String,Double>idfMap=new HashMap<String,Double>();
		
		//遍历给定文件夹下所有文件
		File file=new File(dir);
		File[]files=file.listFiles();
		TOTALDOC=files.length;
		
		//遍历每个文件，并将每个文件中的词切分、唯一化，并将此出现的文档数保存在idfMap中
		for(File f:files) {
			HashSet<String> fileWord=new HashSet<String>();
			String filename=f.getAbsolutePath();
			try {
				FileReader reader = new FileReader(filename);
				BufferedReader br = new BufferedReader(reader);
				String line=null;
				while((line=br.readLine())!=null) {
					//读取文件内容并进入到hashset中，获取该文件所有单词
					String []strArr=line.split(" |、|，|。|；|？|！|,|\\.|;|\\?|!|\"|\\'|\\-|\\(|\\)|]");
					for(int i=0;i<strArr.length;i++) {
						if(strArr[i]==" "||strArr[i]==""||strArr[i]==null)continue;
						fileWord.add(strArr[i]);
					}
					Iterator<String> it=fileWord.iterator();
					while(it.hasNext()) {
						String key=it.next();
						if(idfMap.containsKey(key)) {
							idfMap.put(key, idfMap.get(key)+1);
						}
						else {
							idfMap.put(key,1.0);
						}
					}
				}
				//关闭文件操作
				br.close();
				reader.close();
			}
			//异常处理
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			//计算出所有的词的文档频率后，计算每个次的逆文档频率
			for(Map.Entry<String,Double> entry:idfMap.entrySet()) {
				idfMap.put(entry.getKey(), Math.log10(1.0*TOTALDOC/entry.getValue()));
			}
		}
		return idfMap;
	}
}
