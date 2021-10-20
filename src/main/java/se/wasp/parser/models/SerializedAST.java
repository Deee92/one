package se.wasp.parser.models;

import java.util.List;
import java.util.Map;

public class SerializedAST {
    private List<ASTElement> elements;

    public SerializedAST() {}

    public SerializedAST(List<ASTElement> elements){
        this.elements = elements;
    }

    public List<ASTElement> getElements() {
        return elements;
    }

    public void setElements(List<ASTElement> elements) {
        this.elements = elements;
    }

    public static class ASTElement{
        private String type, parentType;
        private Location location, parentLocation;
        private ExtraInfo extraInfo;

        public ASTElement() {}

        public ASTElement
                (
                        String type,
                        String parentType,
                        Location location,
                        Location parentLocation,
                        ExtraInfo extraInfo
                ){
            this.type = type;
            this.parentType = parentType;
            this.location = location;
            this.parentLocation = parentLocation;
            this.extraInfo = extraInfo;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getParentType() {
            return parentType;
        }

        public void setParentType(String parentType) {
            this.parentType = parentType;
        }

        public Location getParentLocation() {
            return parentLocation;
        }

        public void setParentLocation(Location parentLocation) {
            this.parentLocation = parentLocation;
        }

        public ExtraInfo getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(ExtraInfo extraInfo) {
            this.extraInfo = extraInfo;
        }

        public static class Location{
            private Integer startLine, endLine, startColumn, endColumn;
            private String filePath;

            public Location(){}

            public Location(Integer startLine, Integer endLine, Integer startColumn, Integer endColumn, String filePath){
                this.startLine = startLine;
                this.endLine = endLine;
                this.startColumn = startColumn;
                this.endColumn = endColumn;
                this.filePath = filePath;
            }

            public Integer getEndColumn() {
                return endColumn;
            }

            public void setEndColumn(Integer endColumn) {
                this.endColumn = endColumn;
            }

            public Integer getStartColumn() {
                return startColumn;
            }

            public void setStartColumn(Integer startColumn) {
                this.startColumn = startColumn;
            }

            public Integer getEndLine() {
                return endLine;
            }

            public void setEndLine(Integer endLine) {
                this.endLine = endLine;
            }

            public Integer getStartLine() {
                return startLine;
            }

            public void setStartLine(Integer startLine) {
                this.startLine = startLine;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }
        }

        public static class ExtraInfo{
            private Map<String, String> pairs;

            public ExtraInfo() {}

            public ExtraInfo(Map<String, String> pairs){
                this.pairs = pairs;
            }

            public Map<String, String> getPairs() {
                return pairs;
            }

            public void setPairs(Map<String, String> pairs) {
                this.pairs = pairs;
            }
        }
    }
}
