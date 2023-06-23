package dd.projects.ddshop.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class ImageHostUtil {

    private final Cloudinary cloudinary;

    @Autowired
    public ImageHostUtil(MessageSource messageSource) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", messageSource.getMessage("secret.cloudinary.cloud.name", null, Locale.ENGLISH));
        config.put("api_key", messageSource.getMessage("secret.cloudinary.api.key", null, Locale.ENGLISH));
        config.put("api_secret", messageSource.getMessage("secret.cloudinary.api.secret", null, Locale.ENGLISH));
        cloudinary = new Cloudinary(config);
    }

    public String hostImage(String imageName, String base64Image) {
        try {
            cloudinary.uploader().upload(base64Image, ObjectUtils.asMap("public_id", imageName));
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return cloudinary.url().generate(imageName + ".png");
    }
}
