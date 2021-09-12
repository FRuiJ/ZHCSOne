package com.example.zhcsone.JsonData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SubListData implements Serializable {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private List<DataDTO> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("lineId")
        private int lineId;
        @SerializedName("lineName")
        private String lineName;
        @SerializedName("preStep")
        private PreStepDTO preStep;
        @SerializedName("nextStep")
        private NextStepDTO nextStep;
        @SerializedName("currentName")
        private String currentName;
        @SerializedName("reachTime")
        private int reachTime;

        public int getLineId() {
            return lineId;
        }

        public void setLineId(int lineId) {
            this.lineId = lineId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public PreStepDTO getPreStep() {
            return preStep;
        }

        public void setPreStep(PreStepDTO preStep) {
            this.preStep = preStep;
        }

        public NextStepDTO getNextStep() {
            return nextStep;
        }

        public void setNextStep(NextStepDTO nextStep) {
            this.nextStep = nextStep;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }

        public int getReachTime() {
            return reachTime;
        }

        public void setReachTime(int reachTime) {
            this.reachTime = reachTime;
        }

        public static class PreStepDTO implements Serializable {
            @SerializedName("name")
            private String name;
            @SerializedName("lines")
            private List<LinesDTO> lines;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<LinesDTO> getLines() {
                return lines;
            }

            public void setLines(List<LinesDTO> lines) {
                this.lines = lines;
            }

            public static class LinesDTO implements Serializable {
                @SerializedName("lineId")
                private int lineId;
                @SerializedName("lineName")
                private String lineName;

                public int getLineId() {
                    return lineId;
                }

                public void setLineId(int lineId) {
                    this.lineId = lineId;
                }

                public String getLineName() {
                    return lineName;
                }

                public void setLineName(String lineName) {
                    this.lineName = lineName;
                }
            }
        }

        public static class NextStepDTO implements Serializable {
            @SerializedName("name")
            private String name;
            @SerializedName("lines")
            private List<LinesDTOX> lines;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<LinesDTOX> getLines() {
                return lines;
            }

            public void setLines(List<LinesDTOX> lines) {
                this.lines = lines;
            }

            public static class LinesDTOX implements Serializable {
                @SerializedName("lineId")
                private int lineId;
                @SerializedName("lineName")
                private String lineName;

                public int getLineId() {
                    return lineId;
                }

                public void setLineId(int lineId) {
                    this.lineId = lineId;
                }

                public String getLineName() {
                    return lineName;
                }

                public void setLineName(String lineName) {
                    this.lineName = lineName;
                }
            }
        }
    }
}
