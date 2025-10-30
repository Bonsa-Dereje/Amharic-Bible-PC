const canvas = document.getElementById("canvas");
const svg = document.getElementById("lines");
let scale = 1;

// Draw lines between parent and children
function drawLines() {
  svg.innerHTML = ""; // clear existing
  const parent = document.querySelector(".parent");
  const children = document.querySelectorAll(".child");
  const parentRect = parent.getBoundingClientRect();

  children.forEach((child) => {
    const childRect = child.getBoundingClientRect();
    const x1 = parentRect.left + parentRect.width / 2 - canvas.offsetLeft;
    const y1 = parentRect.top + parentRect.height / 2 - canvas.offsetTop;
    const x2 = childRect.left + childRect.width / 2 - canvas.offsetLeft;
    const y2 = childRect.top + childRect.height / 2 - canvas.offsetTop;

    const line = document.createElementNS("http://www.w3.org/2000/svg", "line");
    line.setAttribute("x1", x1);
    line.setAttribute("y1", y1);
    line.setAttribute("x2", x2);
    line.setAttribute("y2", y2);
    line.setAttribute("stroke", "rgb(242,242,242)");
    line.setAttribute("stroke-width", "2");
    svg.appendChild(line);
  });
}

// Dragging behavior
let activeNode = null;
let offsetX, offsetY;

canvas.addEventListener("mousedown", (e) => {
  if (e.target.classList.contains("node")) {
    activeNode = e.target;
    offsetX = e.offsetX;
    offsetY = e.offsetY;
  }
});

canvas.addEventListener("mousemove", (e) => {
  if (activeNode) {
    activeNode.style.left = e.pageX - offsetX + "px";
    activeNode.style.top = e.pageY - offsetY + "px";
    drawLines();
  }
});

canvas.addEventListener("mouseup", () => {
  activeNode = null;
});

// Zoom with mouse wheel
canvas.addEventListener("wheel", (e) => {
  e.preventDefault();
  const delta = e.deltaY < 0 ? 0.1 : -0.1;
  scale = Math.min(Math.max(scale + delta, 0.5), 2);
  canvas.style.transform = `scale(${scale})`;
  drawLines();
});

window.addEventListener("resize", drawLines);
window.addEventListener("load", drawLines);
