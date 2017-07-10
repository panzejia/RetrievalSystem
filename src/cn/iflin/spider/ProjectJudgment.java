package cn.iflin.spider;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 用来判断项目申报信息标题
 * @author Jaypan
 *
 */
public class ProjectJudgment {
	/**
	 * 对文章标题判断是否为项目申报信息
	 * @return
	 * @throws IOException
	 */
	public static boolean projectJudgment(String text) throws IOException {
        ArrayList<String> wordList = new ArrayList<String>();
        //创建分词对象  
        Analyzer anal=new IKAnalyzer(true);
        StringReader reader=new StringReader(text);  
        //分词  
        TokenStream ts=anal.tokenStream("", reader);  
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
        //遍历分词数据  
        while(ts.incrementToken()){  
        	wordList.add(term.toString());
        }  
        reader.close();  
        int count =0;
        for(int i =0;i<wordList.size();i++){
        	
        	if(projectWordJudgment(wordList.get(i))){
        		count++;
        	}
        }
        if(count>=2){
        	return true;
        }
        return false;
	}
	
	private static boolean projectWordJudgment(String word){
		String stopWordsList[] ={"申报","通知","重大","项目","指南","通告"};//常用项目判定词
		for(int i=0;i<stopWordsList.length;++i)
        {
            if(word.equalsIgnoreCase(stopWordsList[i]))
                return true;
        }
		return false;
	}
}