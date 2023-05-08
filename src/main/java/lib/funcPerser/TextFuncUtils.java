package lib.funcPerser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lib.exception.ParserFailedException;
import model.JsonMapper;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.*;

public class TextFuncUtils {

    ObjectMapper objectMapper = JsonMapper.mapper;
    static Random random = new Random();

    public String Range(double start, double end, double step)
            throws ParserFailedException, JsonProcessingException {
        int count = 0;

        List<Double> list = new ArrayList<>();
        if (start < end && step > 0) {
            for (double i = start; i < end ; i += step) {
                list.add(i);
                count++;
                if (count > 1e6) {
                    throw new ParserFailedException("方法数量长度大于1e6，超过正常限制！");
                }
            }
        }

        if (start > end && step < 0) {
            for (double i = start; i > end ; i += step) {
                list.add(i);
                count++;
                if (count > 1e6) {
                    throw new ParserFailedException("方法数量长度大于1e6，超过正常限制！");
                }
            }
        }
        return objectMapper.writeValueAsString(list);
    }

    public String UUID() throws JsonProcessingException {
        String uuid = java.util.UUID.randomUUID().toString();
        return objectMapper.writeValueAsString(uuid);
    }

    public String randomInt(double num, double start, double end) throws JsonProcessingException {
        List<Object> objects = new ArrayList<>((int) num);
        for(int i = 0; i < (int) num; i++) {
            objects.add(start + random.nextInt((int) end - (int) start));
        }
        return objectMapper.writeValueAsString(objects);
    }

    public String randomInt(double num) throws JsonProcessingException {
        List<Object> objects = new ArrayList<>();
        objects.add(random.nextInt());
        return objectMapper.writeValueAsString(objects);
    }

    public String nowTime(String str) throws JsonProcessingException {
        String format = DateFormatUtils.format(new Date(), str);
        return objectMapper.writeValueAsString(format);
    }

    public String nowTime() throws JsonProcessingException {
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        return objectMapper.writeValueAsString(format);
    }

    public String fillString(double count, String str) throws JsonProcessingException {
        return objectMapper.writeValueAsString(Collections.nCopies((int) count, str));
    }

    public String FillNumber(double count, double num) throws JsonProcessingException {
        return objectMapper.writeValueAsString(Collections.nCopies((int) count, num));
    }

    public String RandomString(double length) throws JsonProcessingException {
        String stringList =
                "的一是了我不人在他有这个上们来到时大地为子中你说生国年着就那和" +
                "要她出也得里后自以会家可下而过天去能对小多然于心学么之都好看起发当没成只如事" +
                "把还用第样道想作种开美总从无情己面最女但现前些所同日手又行意动方期它头经长儿回位" +
                "分爱老因很给名法间斯知世什两次使身者被高已亲其进此话常与活正感见明问力理尔点文几定本" +
                "公特做外孩相西果走将月十实向声车全信重三机工物气每并别真打太新比才便夫再书部水像眼等" +
                "体却加电主界门利海受听表德少克代员许稜先口由死安写性马光白或住难望教命花结乐更" +
                "拉东神记处让母父应直字场平报友关放至张认接告入笑内英军候民岁往何度山觉路带万男边风解" +
                "叫任金快原吃妈变通师立象数四失满战远格士音轻目条呢病始达深完今提求清王化空业思切怎非找片" +
                "罗钱紶吗语元喜曾离飞科言干流欢约各即指合反题必该论交终林请医晚制球决窢传画保读运及则房早院" +
                "量苦火布品近坐产答星精视五连司巴奇管类未朋且婚台夜青北队久乎越观落尽形影红爸百令周吧识步希" +
                "亚术留市半热送兴造谈容极随演收首根讲整式取照办强石古华諣拿计您装似足双妻尼转诉米称丽客南领" +
                "节衣站黑刻统断福城故历惊脸选包紧争另建维绝树系伤示愿持千史谁准联妇纪基买志静阿诗独复痛消社算" +
                "算义竟确酒需单治卡幸兰念举仅钟怕共毛句息功官待究跟穿室易游程号居考突皮哪费" +
                "倒价图具刚脑永歌响商礼细专黄块脚味灵改据般破引食仍存众注笔甚某沉血备习校默务" +
                "土微娘须试怀料调广蜖苏显赛查密议底列富梦错座参八除跑亮假印设线温虽掉京初养香停" +
                "际致阳纸李纳验助激够严证帝饭忘趣支春集丈木研班普导顿睡展跳获艺六波察群皇段急庭创区" +
                "奥器谢弟店否害草排背止组州朝封睛板角况曲馆育忙质河续哥呼若推境遇雨标姐充围案伦护冷警贝" +
                "著雪索剧啊船险烟依斗值帮汉慢佛肯闻唱沙局伯族低玩资屋击速顾泪洲团圣旁堂兵七露园牛哭旅街劳" +
                "型烈姑陈莫鱼异抱宝权鲁简态级票怪寻杀律胜份汽右洋范床舞秘午登楼贵吸责例追较职属渐左录丝牙党继托" +
                "赶章智冲叶胡吉卖坚喝肉遗救修松临藏担戏善卫药悲敢靠伊村戴词森耳差短祖云规窗散迷油旧适乡架恩投弹" +
                "铁博雷府压超负勒杂醒洗采毫嘴毕九冰既状乱景席珍童顶派素脱农疑练野按犯拍征坏骨余承置臓彩灯巨琴免环" +
                "姆暗换技翻束增忍餐洛塞缺忆判欧层付阵玛批岛项狗休懂武革良恶恋委拥娜妙探呀营退摇弄桌熟诺宣银势奖宫" +
                "忽套康供优课鸟喊降夏困刘罪亡鞋健模败伴守挥鲜财孤枪禁恐伙杰迹妹藸遍盖副坦牌江顺秋萨菜划授归浪听凡预奶" +
                "雄升碃编典袋莱含盛济蒙棋端腿招释介烧误";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < (int) length; i++) {
            sb.append(stringList.charAt(random.nextInt(stringList.length() - 1)));
        }
        return objectMapper.writeValueAsString(sb.toString());
    }

    public String RandomChar(double length) throws JsonProcessingException {
        String stringList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < (int) length; i++) {
            sb.append(stringList.charAt(random.nextInt(stringList.length() - 1)));
        }
        return objectMapper.writeValueAsString(sb.toString());
    }

}
