package com.shamanland.lazyfiles.internal

import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.FileMetadata
import com.shamanland.lazyfiles.LazyFilesItem
import com.shamanland.lazyfiles.LazyFilesPlugin

class DropBoxUtilsImpl implements DropBoxUtils {
    @Override
    boolean fetch(String accessToken, LazyFilesItem item) {
        DbxClientV2 client = new DbxClientV2(DropBoxHelper.config, accessToken)
        LazyFilesPlugin.logger.info "DropBox account: " + client.getAccountInfo().displayName

        FileOutputStream os = new FileOutputStream(item.local)

        try {
            LazyFilesPlugin.logger.info "Start fetching: " + item.dropbox
            FileMetadata metadata = client.files().downloadBuilder(item.dropbox).download(os)
            if (metadata == null) {
                throw new AssertionError("Couldn't fetch " + item.dropbox)
            }

            LazyFilesPlugin.logger.info "Fetched: " + fetchedFile
        } finally {
            os.close();
        }

        return false
    }

    @Override
    boolean upload(String accessToken, LazyFilesItem item) {
        DbxClientV2 client = new DbxClientV2(DropBoxHelper.config, accessToken)
        LazyFilesPlugin.logger.info "DropBox account: " + client.getAccountInfo().displayName

        FileInputStream is = new FileInputStream(item.local)

        try {
            LazyFilesPlugin.logger.info "Start uploading: " + item.local.path

            FileMetadata metadata = client.files().uploadBuilder(item.dropbox)
                    .uploadAndFinish(is)
            if (metadata == null) {
                throw new AssertionError("Couldn't upload " + item.local)
            }

            LazyFilesPlugin.logger.info "Uploaded: " + uploadedFile
        } finally {
            is.close();
        }

        return false
    }
}
