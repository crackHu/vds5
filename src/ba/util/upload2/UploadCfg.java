package ba.util.upload2;

public class UploadCfg
{
	public String pathType;
	public String uploadDir;
	public Long maxsize;
    public String directId;
    public String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UploadCfg()
	{
	}
	
	public UploadCfg(String uploadDir,String pathType,Long maxsize,String directId,String userId)
	{
		this.uploadDir=uploadDir;
		this.pathType=pathType;
		this.maxsize=maxsize;
        this.directId = directId;
        this.userId = userId;
	}

	public String getUploadDir()
	{
		return uploadDir;
	}

	public void setUploadDir(String uploadDir)
	{
		this.uploadDir = uploadDir;
	}

	public Long getMaxsize()
	{
		return maxsize;
	}

	public void setMaxsize(Long maxsize)
	{
		this.maxsize = maxsize;
	}

	public String getPathType()
	{
		return pathType;
	}

	public void setPathType(String pathType)
	{
		this.pathType = pathType;
	}

    public String getDirectId()
    {
        return directId;
    }

    public void setDirectId(String directId)
    {
        this.directId = directId;
    }
}
