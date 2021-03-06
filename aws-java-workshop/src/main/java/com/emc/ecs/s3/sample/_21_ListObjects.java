/*
 * Copyright 2013-2018 Dell Inc. or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.emc.ecs.s3.sample;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

public class _21_ListObjects extends BucketAndObjectValidator {

    public static void main(String[] args) throws Exception {
        listObjects(AWSS3Factory.getS3ClientWithV2Signatures(), AWSS3Factory.S3_BUCKET);
        listObjects(AWSS3Factory.getS3ClientWithV4Signatures(), AWSS3Factory.S3_BUCKET_2);
    }

    /**
     * @param s3Client
     * @param bucketName
     */
    private static void listObjects(AmazonS3 s3Client, String bucketName) {
        try {
            ObjectListing objectListing = s3Client.listObjects(bucketName);

            checkListing( "ListObjects", objectListing.getObjectSummaries(), bucketName );
    
            ListObjectsV2Result listing = s3Client.listObjectsV2(bucketName);

            checkListing( "ListObjectsV2", listing.getObjectSummaries(), bucketName );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    /**
     * @param operation
     * @param objectSummaries
     * @param bucketName
     */
    private static void checkListing(String operation, List<S3ObjectSummary> objectSummaries, String bucketName) {
        System.out.println(operation + " found " + objectSummaries.size() + " objects in " + bucketName);
        for (S3ObjectSummary objectSummary : objectSummaries) {
            System.out.println(objectSummary.getKey());
        }
        System.out.println();
    }

}
