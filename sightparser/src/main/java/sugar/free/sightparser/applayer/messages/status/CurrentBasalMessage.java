package sugar.free.sightparser.applayer.messages.status;

import lombok.Getter;
import sugar.free.sightparser.RoundingUtil;
import sugar.free.sightparser.applayer.messages.AppLayerMessage;
import sugar.free.sightparser.applayer.descriptors.Service;
import sugar.free.sightparser.pipeline.ByteBuf;

public class CurrentBasalMessage extends AppLayerMessage {

    private static final long serialVersionUID = 1L;

    @Getter
    private String currentBasalName;
    @Getter
    private float currentBasalAmount = 0;

    @Override
    public Service getService() {
        return Service.STATUS;
    }

    @Override
    public short getCommand() {
        return (short) 0xA905;
    }

    @Override
    protected boolean inCRC() {
        return true;
    }

    @Override
    protected void parse(ByteBuf byteBuf) throws Exception {
        byteBuf.shift(2);
        currentBasalName = byteBuf.readUTF16LE(62);
        currentBasalAmount = RoundingUtil.roundFloat(((float) byteBuf.readUInt16LE()) /  100F, 2);
    }
}
