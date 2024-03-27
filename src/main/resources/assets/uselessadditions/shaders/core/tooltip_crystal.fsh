#version 150

uniform vec4 ColorModulator;
uniform vec4 QuadSize;
uniform float GameTime;

in vec4 vertexColor;
in vec2 texCoord;

out vec4 fragColor;

bool line(float x, float y, float f, float i, float time) {
    float size = sin(time+i*4.0)*0.6;
    float ly = cos(time+x+(i*50.0))*size;
    ly += 0.5;

    return y >= ly - f && y <= ly + f;
}

void mainImage(out vec4 fragColor, in vec2 fragCoord) {
    //Todo: Make this look like crystal shards
    vec2 uv = (fragCoord - QuadSize.xy) / (QuadSize.zw - QuadSize.xy);

    //Vanilla background color; vec(0.063, 0.0, 0.063)
    vec3 col = (vec3(0.2175, 0.155, 0.335) * uv.y) + (vec3(0.406, 0.3135, 0.4765) * (1.0 - uv.y));
    vec3 col2 = col * 1.1;

    fragColor = vec4(col, 0.94);

    float f = 0.01;

    for(float i = 0.0; i < 6.0; i++) {
        if (line(uv.x, uv.y, f, i, GameTime * 1000.0)) {
            fragColor = vec4(col2, 0.94);
        }
    }
}

void main() {
    vec4 color = vec4(1);

    mainImage(color, texCoord);
    fragColor = color;
}
