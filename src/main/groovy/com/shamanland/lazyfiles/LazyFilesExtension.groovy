package com.shamanland.lazyfiles

import com.shamanland.lazyfiles.internal.DropBoxFactory
import com.shamanland.lazyfiles.internal.DropBoxFactoryImpl

import static java.util.Collections.unmodifiableCollection

class LazyFilesExtension {
    String dropboxAccessToken

    List<LazyFilesItem> _fetchItems = new ArrayList<>()

    List<LazyFilesItem> _uploadItems = new ArrayList<>()

    DropBoxFactory _dropboxFactory = new DropBoxFactoryImpl()

    Collection<LazyFilesItem> fetchItems() {
        unmodifiableCollection _fetchItems
    }

    Collection<LazyFilesItem> uploadItems() {
        unmodifiableCollection _uploadItems
    }

    def fetchFile(String dropboxPath, File localFile) {
        _fetchItems.add new LazyFilesItem(localFile, dropboxPath)
    }

    def uploadFile(File localFile, String dropboxPath) {
        _uploadItems.add new LazyFilesItem(localFile, dropboxPath)
    }
}
