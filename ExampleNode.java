package Data;/*
    Copyright (C) 2012 http://software-talk.org/ (developer@software-talk.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * A simple Example implementation of a Node only overriding the sethCosts
 * method; uses manhatten method.
 */
public class ExampleNode extends AbstractNode {

        public ExampleNode(int xPosition, int yPosition) {
            super(xPosition, yPosition);
            // do other init stuff
            //if (xPosition*xPosition-20 + yPosition*yPosition-20 <= 9 )
            //this.setWalkable(false);
            //if (isBlockNode(xPosition,yPosition)){
              //  this.setWalkable(false);

        }

        public void sethCosts(AbstractNode endNode) {
            this.sethCosts((absolute(this.getxPosition() - endNode.getxPosition())
                    + absolute(this.getyPosition() - endNode.getyPosition()))
                    * AbstractNode.BASICMOVEMENTCOST);
        }

        public boolean isBlockNode(int xPosition, int yPosition){
            if ((xPosition-5)*(xPosition-5) + (yPosition-5)*(yPosition-5) <= 9 ) return true;
            else return false;
        }

        private int absolute(int a) {
            return a > 0 ? a : -a;
        }

}
