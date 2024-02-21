package com.illunex.gitbal.batch.util;

import com.illunex.gitbal.batch.ApplicationConstants;
import io.awspring.cloud.s3.Location;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BaseBucketUtil implements EnvironmentAware {
    @Getter
    @Value("${spring.cloud.aws.s3.bucket:factsheet-img}")
    private String bucket;

    public enum BucketPath {
        admin,
        company,
        faq,
        file,
        invest,
        member,
        notice,
        qna,
        report,
        work
    }

    private Environment env;

    /**
     * 프로파일 / 패키지명 / 년 / 월일 /
     * @param bucketPath 1 level folder name
     * @return bucket key
     */
    public String getBucketKeyByEnum(BucketPath bucketPath) {
        return String.join(Location.PATH_DELIMITER, resolveProfile(), bucketPath.name().toLowerCase(), getDatePath());
    }

    /**
     * 프로파일 / 패키지명 / 년 / 월일 / 파일명
     * @param bucketPath 1 level folder name
     * @param ext file extension, ex) .ext
     * @return bucket key
     */
    public String getBucketKeyByEnum(BucketPath bucketPath, String ext) {
        return getBucketKeyByEnum(bucketPath) + UUID.randomUUID() + ext;
    }

    /**
     * Return the first profile explicitly made active for this environment.
     * <p>If no profiles have explicitly been specified as active, then return the 'local'.
     * @return first profile name
     */
    private String resolveProfile() {
        @SuppressWarnings("deprecation")
        Set<String> profiles = new org.springframework.core.Constants(ApplicationConstants.class)
                .getValues("SPRING_PROFILE_").stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());
        return Arrays.stream(env.getActiveProfiles()).filter(profiles::contains).findFirst().orElse("local");
    }

    protected String getDatePath() {
        LocalDate now = LocalDate.now();
        return now.getYear() + Location.PATH_DELIMITER + String.format("%02d%02d", LocalDate.now().getMonthValue(), now.getDayOfMonth()) + Location.PATH_DELIMITER;
    }

    public static String locationToUrlString(Location location) {
        if (location.getVersion() != null) {
            return Location.S3_PROTOCOL_PREFIX + location.getBucket()+ Location.PATH_DELIMITER + location.getObject() + Location.VERSION_DELIMITER + location.getVersion();
        }
        return Location.S3_PROTOCOL_PREFIX + location.getBucket()+ Location.PATH_DELIMITER + location.getObject();
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.env = environment;
    }
}
