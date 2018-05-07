package Bot.Service;

import Bot.DTO.RequestDTO.Messaging;

import java.util.ArrayList;
import java.util.List;

public class MessageTypeDetector {

    private static final String like = "\uD83D\uDC4D";

    private static final String dislike = "\uD83D\uDC4E";

    public boolean isStart(Messaging request) {
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getPayload().equals("GET_STARTED");
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
        return reply.startsWith(like) || reply.startsWith(dislike);
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

    public boolean isHelp(Messaging request){
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getPayload().equals("HELP");
            }
        }
    }

    public boolean isTop(Messaging request){
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getPayload().equals("TOP");
            }
        }
    }

    public boolean isChangeComicsAmound(Messaging request){
        if (request.getPostback() == null) {
            return false;
        } else {
            if (request.getPostback().getPayload() == null) {
                return false;
            } else {
                return request.getPostback().getPayload().equals("AMOUND");
            }
        }
    }

}
