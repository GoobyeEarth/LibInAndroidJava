package library.widget;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.widget.Toast;

public class GoogleDriveClass {
	
	private Activity activity;
	private Context context;
	
	private String previousId;
	 /**
     * Google API client.
     */
    private GoogleApiClient mGoogleApiClient;
	
    /**
     * Request code for auto Google Play Services error resolution.
     */
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    /**
     * Next available request code.
     */
    protected static final int NEXT_AVAILABLE_REQUEST_CODE = 2;
    
    
    
    
	/**
	 *  google drive class shoud called in Activity.onResume() method
	 *  
	 *  because this class is view is called
	 *  
	 *  so dont use in onCreate or onStart method in
	 * @author aberintaro
	 *
	 */
	public GoogleDriveClass(final Activity activity){
		context = activity;
		this.activity = activity;
		
		if (mGoogleApiClient == null) {
			
			GoogleApiClient.Builder builder = new GoogleApiClient.Builder(activity);
			builder.addApi(Drive.API);
			builder.addScope(Drive.SCOPE_FILE);
			builder.addScope(Drive.SCOPE_APPFOLDER);
			builder.addConnectionCallbacks(emptyConnectionCallbacks);
			builder.addOnConnectionFailedListener(failedListener);
			
            mGoogleApiClient = builder.build();
        }
        mGoogleApiClient.connect();
	}
	
	/**
	 * 
	 * @param data
	 * @param title
	 * @return 
	 */
	public void createNewFile(final String title, final String data){
		
		PendingResult<DriveContentsResult>pendingResult = Drive.DriveApi.newDriveContents(mGoogleApiClient);
		
		ResultCallback<DriveContentsResult> driveContentsCallback = new ResultCallback<DriveContentsResult>() {
			
			@Override
			public void onResult(DriveContentsResult result) {
				Toast.makeText(context, "drive callback result", Toast.LENGTH_SHORT).show();
	            if (!result.getStatus().isSuccess()) {
	                
	                return;
	            }
	            final DriveContents driveContents = result.getDriveContents();

	            // Perform I/O off the UI thread.
	            new Thread() {
	                @Override
	                public void run() {
	                    // write content to DriveContents
	                    OutputStream outputStream = driveContents.getOutputStream();
	                    Writer writer = new OutputStreamWriter(outputStream);
	                    
	                    try {
	                        writer.write(data);
	                        writer.close();
	                    } catch (IOException e) {
	                    }
	                    MetadataChangeSet.Builder builder = new MetadataChangeSet.Builder();
	                    builder.setTitle(title);
	                    builder.setMimeType("text/plain");
	                    builder.setStarred(true);

	                    
	                    MetadataChangeSet changeSet = builder.build();
	                    // create a file on root folder
	                     DriveFolder driveFolder = Drive.DriveApi.getRootFolder(mGoogleApiClient);
	                     
	                     
	                     PendingResult<DriveFileResult> file = driveFolder.createFile(mGoogleApiClient, changeSet, driveContents);
	                     file.setResultCallback(fileCallback);
	                     
	                }
	            }.start();
				
			}
		};
		
		pendingResult.setResultCallback(driveContentsCallback);
		
		
	}
	
public void createNewFile(final String title, final String data, final ResultCallback<DriveFileResult> resultCallBack){
		
		PendingResult<DriveContentsResult>pendingResult = Drive.DriveApi.newDriveContents(mGoogleApiClient);
		
		ResultCallback<DriveContentsResult> driveContentsCallback = new ResultCallback<DriveContentsResult>() {
			
			@Override
			public void onResult(DriveContentsResult result) {
				Toast.makeText(context, "drive callback result", Toast.LENGTH_SHORT).show();
	            if (!result.getStatus().isSuccess()) {
	                
	                return;
	            }
	            final DriveContents driveContents = result.getDriveContents();

	            // Perform I/O off the UI thread.
	            new Thread() {
	                @Override
	                public void run() {
	                    // write content to DriveContents
	                    OutputStream outputStream = driveContents.getOutputStream();
	                    Writer writer = new OutputStreamWriter(outputStream);
	                    
	                    try {
	                        writer.write(data);
	                        writer.close();
	                    } catch (IOException e) {
	                    }
	                    MetadataChangeSet.Builder builder = new MetadataChangeSet.Builder();
	                    builder.setTitle(title);
	                    builder.setMimeType("text/plain");
	                    builder.setStarred(true);

	                    
	                    MetadataChangeSet changeSet = builder.build();
	                    // create a file on root folder
	                     DriveFolder driveFolder = Drive.DriveApi.getRootFolder(mGoogleApiClient);
	                     
	                     
	                     PendingResult<DriveFileResult> file = driveFolder.createFile(mGoogleApiClient, changeSet, driveContents);
	                     file.setResultCallback(resultCallBack);
	                     
	                }
	            }.start();
				
			}
		};
		
		pendingResult.setResultCallback(driveContentsCallback);
		
		
	}
	
	public void createNewFile(final String fileName, final String[] data){
PendingResult<DriveContentsResult>pendingResult = Drive.DriveApi.newDriveContents(mGoogleApiClient);
		
		ResultCallback<DriveContentsResult> driveContentsCallback = new ResultCallback<DriveContentsResult>() {
			
			@Override
			public void onResult(DriveContentsResult result) {
				Toast.makeText(context, "drive callback result", Toast.LENGTH_SHORT).show();
	            if (!result.getStatus().isSuccess()) {
	                
	                return;
	            }
	            final DriveContents driveContents = result.getDriveContents();

	            // Perform I/O off the UI thread.
	            new Thread() {

					@Override
	                public void run() {
	                    // write content to DriveContents
	                    OutputStream outputStream = driveContents.getOutputStream();
	                    Writer writer = new OutputStreamWriter(outputStream);
	                    
	                    try {
	                    	
	                    	for(int i = 0; i < data.length; i++) {
	                    		writer.write(data[i] + "\n");
	                    	}
	                    	
	                        
	                        writer.close();
	                    } catch (IOException e) {
	                    }
	                    MetadataChangeSet.Builder builder = new MetadataChangeSet.Builder();
	                    builder.setTitle(fileName);
	                    builder.setMimeType("text/plain");
	                    builder.setStarred(true);

	                    
	                    MetadataChangeSet changeSet = builder.build();
	                    // create a file on root folder
	                     DriveFolder driveFolder = Drive.DriveApi.getRootFolder(mGoogleApiClient);
	                     
	                     
	                     PendingResult<DriveFileResult> file = driveFolder.createFile(mGoogleApiClient, changeSet, driveContents);
	                     file.setResultCallback(fileCallback);
	                     
	                }
	            }.start();
				
			}
		};
		
		pendingResult.setResultCallback(driveContentsCallback);
		
		
		
	}
	
	
	final private ResultCallback<DriveFileResult> fileCallback = new
            ResultCallback<DriveFileResult>() {
        @Override
        public void onResult(DriveFileResult result) {
            if (!result.getStatus().isSuccess()) {
            	
                return;
            }
            
            previousId = result.getDriveFile().getDriveId().toString();
            
        }
    };
	
	public String loadFile(String id){
		
		return null;
	}
	
	public String[] loadFileAsArray(String id){
		return null;
	}
	
	public void rewriteFile(String id, String data){
		
	}
	
	public void rewriteFile(String id, String[] data){
		
	}
	
	public void appendFile(String id, String data){
		
	}
	
	public void appendFile(String id, String[] data){
		
	}
	
	public void callFileByTitle(String title, final FileDataCallBack fileDataCallBack){
		
		Query query = new Query.Builder()
                .addFilter(Filters.contains(SearchableField.TITLE, title))
                .build();
		
		ResultCallback<DriveApi.MetadataBufferResult> metadataCallback =
	            new ResultCallback<DriveApi.MetadataBufferResult>() {
			
	                @Override
	                public void onResult(DriveApi.MetadataBufferResult result) {
	                    if (!result.getStatus().isSuccess()) {
	                        return;
	                    }
	                    
	                    MetadataBuffer buffer = result.getMetadataBuffer();
	                    
	                    fileDataCallBack.fileDataCalled(buffer);
	                    
	                }
	            };
		
        Drive.DriveApi.query(mGoogleApiClient, query).setResultCallback(metadataCallback);
		
	}
	
	public void callTextFilesTitle(final FileDataCallBack fileDataCallBack){
		Query query = new Query.Builder()
                .addFilter(Filters.or(
                        Filters.eq(SearchableField.MIME_TYPE, "text/html"),
                        Filters.eq(SearchableField.MIME_TYPE, "text/plain")))
                .build();
		
		ResultCallback<DriveApi.MetadataBufferResult> metadataCallback =
	            new ResultCallback<DriveApi.MetadataBufferResult>() {
			
	                @Override
	                public void onResult(DriveApi.MetadataBufferResult result) {
	                    if (!result.getStatus().isSuccess()) {
	                        return;
	                    }
	                    
	                    MetadataBuffer buffer = result.getMetadataBuffer();
	                    fileDataCallBack.fileDataCalled(buffer);
	                    
	                    
	                }
	            };
	            
	            
		
        Drive.DriveApi.query(mGoogleApiClient, query).setResultCallback(metadataCallback);
        
	}
	public interface TitleCallBack{
    	public void titleCalled(String[] str);
    }
	
	public interface FileDataCallBack{
		public void fileDataCalled(MetadataBuffer files);
	}
	
	
	
	private GoogleApiClient.ConnectionCallbacks emptyConnectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
		
		@Override
		public void onConnectionSuspended(int cause) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onConnected(Bundle connectionHint) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private GoogleApiClient.OnConnectionFailedListener failedListener = new GoogleApiClient.OnConnectionFailedListener() {
		
		@Override
		public void onConnectionFailed(ConnectionResult result) {
			Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
	        if (!result.hasResolution()) {
	            // show the localized error dialog.
	            GoogleApiAvailability.getInstance().getErrorDialog(activity, result.getErrorCode(), 0).show();
	            return;
	        }
	        try {
	            result.startResolutionForResult(activity, REQUEST_CODE_RESOLUTION);
	        } catch (SendIntentException e) {
	            
	        }
			
			
		}
	};
}
