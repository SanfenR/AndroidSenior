
## 简介

> Shader就是着色器的意思。我们可以这样理解，Canvas中的各种drawXXX方法定义了图形的形状，画笔中的Shader则定义了图形的着色、外观，二者结合到一起就决定了最终Canvas绘制的被色彩填充的图形的样子。类android.graphics.Shader有五个子类，分别是：BitmapShader、LinearGradient、RadialGradient、SweepGradient和ComposeShader，下面依次对这几个类的使用分别说明。

### BitmapShader

- BitmapShader，用Bitmap对绘制的图形进行渲染着色，其实就是用图片对图形进行贴图。

```java
//构造函数
BitmapShader(Bitmap bitmap, Shader.TileMode tileX, Shader.TileMode tileY)
```

1. CLAMP: 当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
2. REPEAT: 当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域。
3. MIRROR: 当绘制的图形尺寸大于Bitmap尺寸时，MIRROR也会用Bitmap重复平铺整个绘图区域，与REPEAT不同的是，两个相邻的Bitmap互为镜像。

实现代码:

```java
    private Shader.TileMode mDefaultTileMode = Shader.TileMode.REPEAT;

    public void drawBitmap(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music_player);
        BitmapShader bitmapShader = new BitmapShader(bitmap,
                mDefaultTileMode,
                mDefaultTileMode
        );
        mPaint.setShader(bitmapShader);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0,  canvas.getWidth(), canvas.getHeight(), mPaint);
    }

```
效果如下:
<img width="180" src="http://ohqvqufyf.bkt.clouddn.com/2016-12-22%2015_28_26_bitmap.gif">


### LinearGradient
- LinearGradient, 是用来创建线性渐变效果的，它是沿着某条直线的方向渐变的，坐标(x0,y0)就是这条渐变直线的起点，坐标(x1,y1)就是这条渐变直线的终点。需要说明的是，坐标(x0,y0)和坐标(x1,y1)都是Canvas绘图坐标系中的坐标。color0和color1分别表示了渐变的起始颜色和终止颜色。与BitmapShader类似，LinearGradient也支持TileMode。

- LinearGradient有以下三个取值：CLAMP 、REPEAT 和 MIRROR

```java
    LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile)
    LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions, Shader.TileMode tile)
```

```java
  private Shader.TileMode mDefaultTileMode = Shader.TileMode.REPEAT;

  @Override
  protected void onDraw(Canvas canvas) {
      canvas.drawRect(0, 0, getWidth() ,  getHeight(), mPaint);
  }

  public void drawLinear(){
      LinearGradient linearGradient = new LinearGradient(
              0, 0, getWidth() / 4, getHeight() / 4,
              Color.GREEN,
              Color.BLUE,
              mDefaultTileMode
      );
      mPaint.setShader(linearGradient);
  }
```
效果如下：
<img width="180" src="http://ohqvqufyf.bkt.clouddn.com/2016-12-22%2015_29_08_linear.gif">


### RadialGradient

- RadialGradient 径向渐变，径向渐变说的简单点就是个圆形中心向四周渐变的效果

```java
RadialGradient(float centerX, float centerY, float radius, int centerColor, int edgeColor, Shader.TileMode tileMode)

RadialGradient(float centerX, float centerY, float radius, int[] colors, float[] stops, Shader.TileMode tileMode)
```
RadialGradient是用来创建从中心向四周发散的辐射渐变效果的，所以我们需要在其构造函数中传入一些圆的参数，坐标(centerX,centerY)是圆心，即起始的中心颜色的位置，radius确定了圆的半径，在圆的半径处的颜色是edgeColor，这样就确定了当位置从圆心移向圆的轮廓时，颜色逐渐从centerColor渐变到edgeColor。RadialGradient也支持TileMode参数，有以下三个取值：CLAMP 、REPEAT 和 MIRROR。

```java
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0 , 0, getWidth(), getHeight(), mPaint);
    }

    public void drawRadial(){
        RadialGradient radial = new RadialGradient(
                getWidth() / 2, getHeight() / 2, getWidth() / 4,
                Color.YELLOW, Color.RED,
                mDefaultTileMode
        );

        mPaint.setShader(radial);
    }
```
效果如下：
<img width="180" src="http://ohqvqufyf.bkt.clouddn.com/2016-12-22%2015_30_05_radiol.gif">

### SweepGradient
SweepGradient可以用来创建360度颜色旋转渐变效果，具体来说颜色是围绕中心点360度顺时针旋转的，起点就是3点钟位置。

- SweepGradient, 梯度渐变，也称之为扫描式渐变，因为其效果有点类似雷达的扫描效果.

```java
    SweepGradient(float cx, float cy, int color0, int color1)
    SweepGradient(float cx, float cy, int[] colors, float[] positions)
```

SweepGradient不支持TileMode参数, 坐标(cx,cy)决定了中心点的位置，会绕着该中心点进行360度旋转。color0表示的是起点的颜色位置，color1表示的是终点的颜色位置。

```java

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, mPaint);
    }


    public void drawSweep(){
        //SweepGradient sweep = new SweepGradient(getWidth() /2, getHeight() / 2, Color.GREEN, Color.BLUE);
        int[] colors = {Color.RED, Color.WHITE, Color.YELLOW};
        float[] positions = {0f, 0.75f, 0f};
        SweepGradient sweep = new SweepGradient(getWidth() / 2 , getHeight() / 2, colors, positions);

        mPaint.setShader(sweep);
    }
```
效果如下：
<img width="180" src="http://ohqvqufyf.bkt.clouddn.com/2016-12-22%2015_30_42_sweep.gif">

## 参考

[自定义控件其实很简单1/3](http://blog.csdn.net/aigestudio/article/details/41799811)

[Android中Canvas绘图之Shader使用图文详解](http://blog.csdn.net/iispring/article/details/50500106)