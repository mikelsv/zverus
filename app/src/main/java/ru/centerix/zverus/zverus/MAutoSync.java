package ru.centerix.zverus.zverus;
import android.os.AsyncTask;

class MAutoAsyncFunc{
	MAutoAsync ma;
	public void execute(){
		return ;
	}
}

class MAutoAsync extends AsyncTask<Void, Integer, Void>{
	MAutoAsyncFunc pre, bg, post, prog;
	int result;

	public MAutoAsync(){}

	public void setPre(MAutoAsyncFunc func){
		pre=func;
	}

	public void setBg(MAutoAsyncFunc func){
		bg=func;
	}

	public void setPost(MAutoAsyncFunc func){
		post=func;
	}

	public void setProg(MAutoAsyncFunc func){
		prog=func;
	}

	public void setProgress(int p){
		publishProgress(p);
	}

	@Override
	protected void onPreExecute(){
		super.onPreExecute();
		if(pre==null)
			return ;
		pre.ma=this;
		pre.execute();
	}

	@Override
	protected Void doInBackground(Void... params){
		if(bg==null)
			return null;
		bg.ma=this;
		bg.execute();
		return null;
	}

	@Override
	protected void onPostExecute(Void result){
		super.onPostExecute(result);
		if(post==null)
			return ;
		post.ma=this;		
		post.execute();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		//super.onProgress(values[0]);
		if(prog==null)
			return ;
		prog.ma=this;		
		prog.execute();
	}
}



