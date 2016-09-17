package ba.util.upload2;

public class Item
{
	public String fileName;
	public String filePath;
	public String fileFullPath;
	public boolean uploadSuccess;

	public Item()
	{
	}
	
	public Item(String fileName,String filePath,String fileFullPath,boolean uploadSuccess)
	{
		this.fileName=fileName;
		this.filePath=filePath;
		this.fileFullPath=fileFullPath;
		this.uploadSuccess=uploadSuccess;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public boolean isUploadSuccess()
	{
		return uploadSuccess;
	}

	public void setUploadSuccess(boolean uploadSuccess)
	{
		this.uploadSuccess = uploadSuccess;
	}

	public String getFileFullPath()
	{
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath)
	{
		this.fileFullPath = fileFullPath;
	}
}
