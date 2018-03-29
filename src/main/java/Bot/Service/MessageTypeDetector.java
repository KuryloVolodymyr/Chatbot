package Bot.Service;

import Bot.DTO.RequestDTO.Messaging;

import java.util.ArrayList;
import java.util.List;

public class MessageTypeDetector {
    public boolean isStart(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getPayload().equals("FACEBOOK_WELCOME");
            }
        }
    }

    public boolean isGetComics(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getTitle().equals("Comics");
            }
        }
    }

    public boolean isImage(Messaging request) {
        if (request.getMessage() == null) {
            return false;
        } else {
            if (request.getMessage().getAttachments() == null) {
                return false;
            } else {
                if (request.getMessage().getAttachments().get(0).getType().equals("image")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public boolean isText(Messaging request) {
        if (request.getMessage() == null) {
            return false;
        } else {
            if (request.getMessage().getText() == null) {
                return false;
            } else
                return true;
        }
    }

    public boolean isRate(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getTitle().equals("Rate");
            }
        }
    }

    public boolean isQuickReply(Messaging request) {
        if (request.getMessage() == null) {
            return false;
        } else {
            if (request.getMessage().getQuickReply() == null) {
                return false;
            } else
                return true;
        }
    }

    public boolean isHeroQuickReply(Messaging request) {
        return request.getMessage().getQuickReply().getPayload().equals("hero");
    }

    public boolean isRatingQuickReply(Messaging request) {
        String reply = request.getMessage().getText();
        return reply.equals("\uD83D\uDC4D") || reply.equals("\uD83D\uDC4E");
    }

    public boolean isMoreComics(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getTitle().equals("More comics");
            }
        }
    }

}
